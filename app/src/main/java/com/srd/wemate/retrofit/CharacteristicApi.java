package com.srd.wemate.retrofit;

import com.srd.wemate.model.Characteristic;
import com.srd.wemate.model.Profile;
import com.srd.wemate.model.dto.profile.ProfileUpdateRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CharacteristicApi {

    @GET("/characteristic/get-all")
    Call<List<Characteristic>> getAllProfile();

    @POST("/characteristic/save")
    Call<Characteristic> save(@Body Characteristic characteristic);

//    @PUT("/characteristic/{id}")
//    Call<String> update(@Path("id") String id, @Body ProfileUpdateRequestDto requestDto);

    @GET("/characteristic/{id}")
    Call<Characteristic> findById(@Path("id") String id);


}
