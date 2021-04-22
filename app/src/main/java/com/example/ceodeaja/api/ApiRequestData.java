package com.example.ceodeaja.api;

import com.example.ceodeaja.model.ResponModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestData {
    @GET("animal/show")
    Call<ResponModel> findAllAnimals();

    @FormUrlEncoded
    @POST("animal/create")
    Call<ResponModel> createAnimal(
            @Field("name") String name,
            @Field("color") String color,
            @Field("description") String description
    );
}
