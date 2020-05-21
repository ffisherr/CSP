package com.ffisherr.csp.retrofit.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ffisherr.csp.TechUserActivity;
import com.ffisherr.csp.retrofit.ServerApi;
import com.ffisherr.csp.retrofit.body.AutorisationBody;
import com.ffisherr.csp.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ffisherr.csp.Confnig.*;

public class FindUserController  implements Callback<User> {

    private Context mainContext;

    public void start(Context context, String login, String password) {
        mainContext = context;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Log.i("FindUSerController", "Cjplftv");
        AutorisationBody body = new AutorisationBody(login, password);
        Call<User> call = serverApi.loginUser(body);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()) {
            User userA = response.body();
            System.out.println(userA);
            if (userA.getRoleId() == 1) {
                Log.i("FindUSerController", "Вошли");
                Intent intent = new Intent(mainContext, TechUserActivity.class);
                mainContext.startActivity(intent);
            } else {
                Toast.makeText(mainContext, "Unknown user", Toast.LENGTH_LONG).show();
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
