package com.android.batdemir.library.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CallTest {
    @GET("user/v1/Login")
    Call<Object> callTest(
            @Header("UrunYili") String cropYear,
            @Query("userName") String userName,
            @Query("isPurchaseLogin") boolean isPurchaseLogin
    );
}
