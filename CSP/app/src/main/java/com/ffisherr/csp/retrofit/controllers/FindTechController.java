package com.ffisherr.csp.retrofit.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ffisherr.csp.retrofit.ElementsFounded;
import com.ffisherr.csp.retrofit.ServerApi;
import com.ffisherr.csp.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ffisherr.csp.Confnig.BASE_URL;

public class FindTechController  implements Callback<List<User>> {

    private Context mainContext;
    private ElementsFounded listener;

    public void start(Context context) {
        mainContext = context;
        this.listener = (ElementsFounded)mainContext;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<List<User>> call = serverApi.getTechUsers();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if(response.isSuccessful()) {
            Log.e("", "Данные получены здесь");
            List<User> users = response.body();
            if (listener != null) {
                listener.TechUsersFounded(users);
            }
        } else {
            Toast.makeText(mainContext, "Произошла ошибка при получении данных от сервера",
                    Toast.LENGTH_LONG).show();
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        t.printStackTrace();
    }
}
