package com.android.batdemir.mylibrary.api;

import android.annotation.SuppressLint;
import android.util.Log;

import com.android.batdemir.mylibrary.tools.GlobalVariable;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String baseUrl = "";
    private static Retrofit retrofit = null;
    private static boolean ssl = false;

    private RetrofitClient() {

    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(getGsonConverterFactory())
                    .client(ssl ? getUnsafeOkHttpClient() : getHttpClient())
                    .build();
        }
        return retrofit;
    }

    public static void setBaseUrl(String newBaseUrl) {
        if (!baseUrl.equals(newBaseUrl)) {
            baseUrl = newBaseUrl;
            retrofit = null;
            retrofit = getInstance();
        }
    }

    public static void setSll(boolean newSsl) {
        if (ssl != newSsl) {
            ssl = newSsl;
            retrofit = null;
            retrofit = getInstance();
        }
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

    private static OkHttpClient getUnsafeOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.readTimeout(1, TimeUnit.MINUTES);

        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor).build();

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            Log.v(RetrofitClient.class.getSimpleName(), authType);
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                            Log.v(RetrofitClient.class.getSimpleName(), authType);
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier((hostname, session) -> Boolean.TRUE);
        } catch (Exception e) {
            Log.e(RetrofitClient.class.getSimpleName(), e.getMessage());
        }

        return builder.build();
    }
}
