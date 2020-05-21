package com.ffisherr.csp.retrofit.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ffisherr.csp.TechUserActivity;
import com.ffisherr.csp.retrofit.ServerApi;
import com.ffisherr.csp.techno.ApplicationForm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ffisherr.csp.Confnig.BASE_URL;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_CREATOR_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_DATE_WHEN_CLOSED;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_DATE_WHEN_CREATED;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_EQUIPMENT_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_PHONE_NUMBER;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_SOLVER_ID;
import static com.ffisherr.csp.Confnig.EXTRA_JOB_STATUS;

public class FindJobForTechController  implements Callback<ApplicationForm> {

    private Context mainContext;

    public void start(Context context, int id) {
        mainContext = context;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ServerApi serverApi = retrofit.create(ServerApi.class);
        Call<ApplicationForm> call = serverApi.getJobForTech(id);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ApplicationForm> call, Response<ApplicationForm> response) {
        if(response.isSuccessful()) {
            ApplicationForm newForm = response.body();
            System.out.println(newForm);
            Intent intent = new Intent(mainContext, TechUserActivity.class);
            intent.putExtra(EXTRA_JOB_ID,                newForm.getId());
            intent.putExtra(EXTRA_JOB_CREATOR_ID,        newForm.getUserCreatorId());
            intent.putExtra(EXTRA_JOB_SOLVER_ID,         newForm.getUserSolverId());
            intent.putExtra(EXTRA_JOB_DATE_WHEN_CLOSED,  newForm.getWhenClosed());
            intent.putExtra(EXTRA_JOB_DATE_WHEN_CREATED, newForm.getWhenCreated());
            intent.putExtra(EXTRA_JOB_EQUIPMENT_ID,      newForm.getEquipmentId());
            intent.putExtra(EXTRA_JOB_STATUS,            newForm.getStatus());
            intent.putExtra(EXTRA_JOB_PHONE_NUMBER,      newForm.getPhoneNumber());
            mainContext.startActivity(intent);

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ApplicationForm> call, Throwable t) {
        t.printStackTrace();
    }
}
