package com.srd.wemate.retrofit;

import com.srd.wemate.model.dto.posts.PostsListResponseDto;
import com.srd.wemate.model.dto.posts.PostsResponseDto;
import com.srd.wemate.model.dto.posts.PostsSaveRequestDto;
import com.srd.wemate.model.dto.posts.PostsUpdateRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostsApi {

    @POST("/posts")
    Call<Long> save(@Body PostsSaveRequestDto requestDto);

    @GET("/posts")
    Call<List<PostsListResponseDto>> findAllDesc();

    @PUT("/posts/{id}")
    Call<Long> update(@Path("id") Long id, @Body PostsUpdateRequestDto requestDto);

    @DELETE("/posts/{id}")
    Call<Void> delete(@Path("id") Long id);

    @GET("/posts/{id}")
    Call<PostsResponseDto> findById(@Path("id") Long id);

}
