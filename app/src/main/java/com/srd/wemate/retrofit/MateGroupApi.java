package com.srd.wemate.retrofit;

import com.srd.wemate.model.dto.mategroup.MateGroupAddRequestDto;
import com.srd.wemate.model.dto.mategroup.MateGroupDeleteRequestDto;
import com.srd.wemate.model.dto.mategroup.MateGroupResponseDto;
import com.srd.wemate.model.dto.mategroup.MateGroupSaveRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MateGroupApi {

    @POST("/mateGroup")
    Call<Integer> save(@Body MateGroupSaveRequestDto requestDto);

    @PUT("/mateGroup/add/{id}")
    Call<Integer> addMate(@Path("id") int id, @Body MateGroupAddRequestDto requestDto);

    @PUT("/mateGroup/delete/{id}")
    Call<Integer> deleteMate(@Path("id") int id, @Body MateGroupDeleteRequestDto requestDto);

    @DELETE("/mateGroup/{id}")
    Call<Void> delete(@Path("id") int id);

    @GET("/posts/{id}")
    Call<MateGroupResponseDto> findById(@Path("id") int id);
}
