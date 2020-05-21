package com.ffisherr.csp.retrofit.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
            System.out.println(userA.getId());
            SharedPreferences sharedPreferences = mainContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(PREFERENCE_ID,      userA.getId());
            editor.putInt(PREFERENCE_ROLE_ID, userA.getRoleId());
            editor.putString(PREFERENCE_FIRST_NAME, userA.getFirstName());
            editor.putString(PREFERENCE_SUR_NAME, userA.getSurName());
            editor.putString(PREFERENCE_SECOND_NAME, userA.getSecondName());
            editor.commit();
            switch (userA.getRoleId()) {
                case 0:
                    System.out.println("Usual user");
                    break;
                case 1:
                    System.out.println("Tech support");
                    FindJobForTechController jobController = new FindJobForTechController();
                    jobController.start(mainContext, userA.getId());
                    break;
                case 2:
                    System.out.println("Chief user");
                    break;
                case 3:
                    System.out.println("Tech TeamLead");
                    break;
                default:
                    System.out.println("Unknown role");
                    break;
            }
        } else {
            Toast.makeText(mainContext, "Произошла ошибка при получении данных от сервера",
                    Toast.LENGTH_LONG).show();
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        t.printStackTrace();
    }
}
