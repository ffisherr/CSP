package com.ffisherr.csp.retrofit;

import com.ffisherr.csp.users.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @GET("auth/id/{id}")
    Call<User> authUser(@Path("id") int id);

}
