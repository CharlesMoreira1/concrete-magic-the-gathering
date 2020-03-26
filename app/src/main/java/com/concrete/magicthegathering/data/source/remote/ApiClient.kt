package com.concrete.magicthegathering.data.source.remote

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(val context: Context) {

    companion object {
        const val BASE_URL = "https://api.magicthegathering.io/v1/"
    }

    private val cacheSize = (50 * 1024 * 1024).toLong()

    private val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.MINUTES)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, cacheSize))
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(networkCacheInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}