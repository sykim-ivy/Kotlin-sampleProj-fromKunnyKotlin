package com.androidhuman.example.simplegithub.api;

import android.support.annotation.NonNull;

import com.androidhuman.example.simplegithub.api.model.GithubAccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Github OAuth API 인터페이스 (Retrofit용)
 * : 사용자의 계정 인증후 redirect되면서 access token을 받도록 요청하는 API
 * (https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
 *
 * - Retrofit 사용
 *    Retrofit 에서는 사용할 API를 인터페이스로 선언
 *     ㄴ> 호출방식, 경로, 반환되는 데이터 타입 등을 지정
 */
public interface AuthApi {
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    Call<GithubAccessToken> getAccessToken(
            @NonNull @Field("client_id") String clientId,
            @NonNull @Field("client_secret") String clientSecret,
            @NonNull @Field("code") String code);

}
