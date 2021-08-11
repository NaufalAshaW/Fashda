package com.nasha.fashda.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiEndpoint {

    private static Retrofit prayRetrofit;
    private static Retrofit placeRetrofit;
    private static OkHttpClient httpClient;

    public static Retrofit getPrayClient(){

        // init retrofit
        Gson gson = new GsonBuilder().setLenient().create();
        prayRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.PRAY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return prayRetrofit;
    }

    public static Retrofit getPlaceClient() {
        if (placeRetrofit == null && httpClient == null) {

            // add global parameters for api key
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl originalHttpUrl = original.url();

                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addQueryParameter("key", ApiConfig.API_KEY)
                                    .build();

                            Request.Builder requestBuilder = original.newBuilder()
                                    .url(url);

                            Request request = requestBuilder.build();

                            return chain.proceed(request);
                        }
                    })
                    .build();

            // init retrofit
            Gson gson = new GsonBuilder().setLenient().create();
            placeRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.PLACE_BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return placeRetrofit;
    }

}
