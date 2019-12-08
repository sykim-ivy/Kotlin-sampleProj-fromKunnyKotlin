package com.androidhuman.example.simplegithub.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Github OAuth API 인터페이스 응답값에 대응되는 클래스
 *
 * Gson 라이브러리를 이용해 Retrofit API의 JSON 응답을 클래스형태로 받을 클래스
 * - Gson 라이브러리
 *    Retrofit을 통해 JSON 형태로 받은 응답을 Gson 라이브러리를 이용해 클래스 형태로 변환하여 받음
 *    ㄴ> JSON 필드와 Gson 라이브러리 클래스 필드명 일치 시, 데이터 자동 매핑
 *    ㄴ> JSON 필드와 Gson 라이브러리 클래스 필드명 불일치 시, SerializedName어노테이션을 이용
 *          ex) @SerializedName("매핑할 JSON 필드명")
 */
public final class GithubAccessToken {

    @SerializedName("access_token")
    public final String accessToken;

    public final String scope;

    @SerializedName("token_type")
    public final String tokenType;

    public GithubAccessToken(String accessToken, String scope, String tokenType) {
        this.accessToken = accessToken;
        this.scope = scope;
        this.tokenType = tokenType;
    }
}
