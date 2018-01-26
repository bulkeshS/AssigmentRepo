package com.assignment.api;

import com.assignment.model.Address;
import com.assignment.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppService {
    @GET("/users")
    Call<List<Example>> getAnswers();

}
