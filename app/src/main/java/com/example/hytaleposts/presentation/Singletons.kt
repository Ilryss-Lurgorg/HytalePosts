package com.example.hytaleposts.presentation

import com.example.hytaleposts.presentation.HytalePostApplication.Companion.context
import com.example.hytaleposts.presentation.api.HytalePostAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons {
    companion object {
        var cache = Cache(File(context?.cacheDir, "responses"),10*1024*1024) //10 MiB

        val okhttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        val hytalePostApi: HytalePostAPI = Retrofit.Builder()
            .baseUrl("https://hytale.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
            .create(HytalePostAPI::class.java)
    }
}