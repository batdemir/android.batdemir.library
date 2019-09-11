package com.android.batdemir.mylibrary.API;

import com.android.batdemir.mylibrary.GlobalVariable;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String baseUrl = "";
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getHttpClient())
                    .build();
        }
        return retrofit;
    }

    public static void setBaseUrl(String newBaseUrl){
        if(!baseUrl.equals(newBaseUrl)){
            baseUrl = newBaseUrl;
            retrofit = getInstance();
        }
    }

    private RetrofitClient(){

    }

    private static GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .setDateFormat(GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT.toString())
                        .create()
        );
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.readTimeout(1, TimeUnit.MINUTES);

        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        return builder.build();
    }
}
