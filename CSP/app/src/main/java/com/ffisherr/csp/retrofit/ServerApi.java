package com.ffisherr.csp.retrofit;

import com.ffisherr.csp.retrofit.body.AutorisationBody;
import com.ffisherr.csp.retrofit.body.RegisterBody;
import com.ffisherr.csp.techno.ApplicationForm;
import com.ffisherr.csp.users.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @GET("auth/id/{id}")
    Call<User> authUser(@Path("id") int id);

    @POST("auth/user/")
    Call<User> loginUser(@Body AutorisationBody body);

    @POST("auth/register/")
    Call<User> registerUser(@Body RegisterBody u);


    @GET("techno/id/{id}")
    Call<ApplicationForm> getJobForTech(@Path("id") int id);

    @GET("techno/users/")
    Call<List<User>> getTechUsers();

}
