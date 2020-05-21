package com.ffisherr.csp.retrofit;

import com.ffisherr.csp.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ffisherr.csp.Confnig.*;

public class Controller  implements Callback<User> {

    public void start(int id) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ServerApi serverApi = retrofit.create(ServerApi.class);

        //String call = serverApi.authUser(0);
        Call<User> call = serverApi.authUser(id);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()) {
            User userA = response.body();
            System.out.println(userA);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        t.printStackTrace();
    }
}
