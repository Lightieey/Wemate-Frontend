package com.srd.wemate.retrofit;

import com.srd.wemate.model.Profile;
import com.srd.wemate.model.dto.posts.PostsResponseDto;
import com.srd.wemate.model.dto.profile.ProfileListResponseDto;
import com.srd.wemate.model.dto.profile.ProfileUpdateRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileApi {

    @GET("/profile/get-all")
    Call<List<Profile>> getAllProfile();

    @POST("/profile/save")
    Call<Profile> save(@Body Profile profile);

    @PUT("/profile/{id}")
    Call<String> update(@Path("id") String id, @Body ProfileUpdateRequestDto requestDto);

    @GET("/profile/{id}")
    Call<Profile> findById(@Path("id") String id);

    @GET("/profile/gid/{id}")
    Call<List<ProfileListResponseDto>> findByGid(@Path("id") int id);

}
