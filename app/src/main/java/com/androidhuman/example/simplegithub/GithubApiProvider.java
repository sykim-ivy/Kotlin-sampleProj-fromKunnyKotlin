package com.androidhuman.example.simplegithub;

import android.support.annotation.NonNull;

import com.androidhuman.example.simplegithub.api.AuthApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubApiProvider {

    // access token 획득을 위한 API 객체를 생성
    public static AuthApi provideAuthApi() {
        return new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .client(provideOkHttpClient(provideLoggingInterceptor(), null))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApi.class);
    }

    /**
     * 네트워크 통신에 사용할 client객체 생성
     * @param interceptor
     * @return
     */
    private static OkHttpClient provideOkHttpClient(
            @NonNull HttpLoggingInterceptor interceptor,
            @NonNull AuthInterceptor authInterceptor) {
        OkHttpClient.Builder b = new OkHttpClient.Builder();

        if(null != authInterceptor) {
            // 매 request의 header에 access token정보를 추가
            b.addInterceptor(authInterceptor);
        }

        // 이 client를 통해 오고 가는 네트워크 req/res를 로그에 표시하도록 함
        b.addInterceptor(interceptor);

        return b.build();
    }


    /**
     * 네트워크 req/res를 로그에 표시하는 Interceptor 객체 생성
     * : OkHttp는 HttpLoggingInterceptor로 HTTP req/res 데이터를 기록함
     * @return
     */
    private static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * request 헤더에 access token 정보를 추가하는 Application Interceptor 클래스
     * : Interceptor는 OkHttp에 있는 강력한 메커니즘으로 네트워크 도중 무언가를 실어보내거나 가져올 수 있음
     *
     * Application Interceptor : 어플리케이션과 Okhttp Core 사이의 네트워크 req/res를 가로챔
     *  cf) Network Interceptor : Okhttp Core과 Network 사이의 네트워크 req/res를 가로챔
     *  (https://square.github.io/okhttp/interceptors/)
     *
     */
    static class AuthInterceptor implements Interceptor {

        private final String token;

        AuthInterceptor(String token) {
            this.token = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            // request 헤더에 access token 정보를 추가
            Request.Builder b = original.newBuilder()
                    .addHeader("Authorization", "token "+token);

            Request request = b.build();
            return chain.proceed(request); // 모든 HTTP 작업이 이루어지는 곳에서 요청을 충족시키는 응답을 생성
        }
    }

}
