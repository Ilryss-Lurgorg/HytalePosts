package com.example.hytaleposts.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HytalePostAPI {
    @GET("blog/post/published")
    fun getHytalePostList(): Call<List<HytalePostResponse>>

    @GET("blog/post/slug/{post}")
    fun getHytalePostDetailBySlug(@Path("post")slug: String): Call<HytalePostResponse>
}