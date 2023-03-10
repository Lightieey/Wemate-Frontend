package com.srd.wemate.retrofit;

import com.srd.wemate.model.dto.posts.PostsResponseDto;
import com.srd.wemate.model.dto.rule.RuleResponseDto;
import com.srd.wemate.model.dto.rule.RuleSaveRequestDto;
import com.srd.wemate.model.dto.rule.RuleUpdateRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RuleApi {

    @POST("/rule")
    Call<Integer> save(@Body RuleSaveRequestDto requestDto);

    @PUT("/rule/{id}")
    Call<Integer> update(@Path("id") int id, @Body RuleUpdateRequestDto requestDto);

    @GET("/rule/{id}")
    Call<RuleResponseDto> findById(@Path("id") int id);

}
