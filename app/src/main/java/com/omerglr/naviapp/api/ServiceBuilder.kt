package com.omerglr.lovelica.api

import android.app.Activity
import android.util.Log
import com.omerglr.naviapp.BASE_URL
import com.omerglr.naviapp.utils.AccessTokenUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private lateinit var YOUR_TOKEN: String;
    private val TAG = "ServiceBuilder";

    private fun client(debug: Boolean): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        if (debug) {
            return (OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer ${YOUR_TOKEN}")
                        .build()
                    chain.proceed(request)
                }.build());
        }


        return OkHttpClient.Builder().build()
    }


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client(true))
        .build()

        .create(EndPoints::class.java)

    fun buildService(activity: Activity): EndPoints {

        YOUR_TOKEN = AccessTokenUtils.getAccessToken(activity);
        Log.d(TAG, "Service Has built.")
        return retrofit
    }
}