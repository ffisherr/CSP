package com.ffisherr.csp.retrofit.controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ffisherr.csp.Enter;
import com.ffisherr.csp.retrofit.ServerApi;
import com.ffisherr.csp.retrofit.body.RegisterBody;
import com.ffisherr.csp.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ffisherr.csp.Confnig.BASE_URL;

public class RegisterUserController implements Callback<User> {

    private Context mainContext;

    public void start(Context context, RegisterBody user) {
        mainContext = context;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<User> call = serverApi.registerUser(user);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()) {
            User userRegistered = response.body();
            System.out.println(userRegistered);
            if (userRegistered.getId() > -1) {
                Toast.makeText(mainContext, "Регистрация успешна", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mainContext, Enter.class);
                mainContext.startActivity(intent);
            } else {
                Toast.makeText(mainContext, "Регистрация провалена", Toast.LENGTH_LONG).show();
            }

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        t.printStackTrace();
    }
}