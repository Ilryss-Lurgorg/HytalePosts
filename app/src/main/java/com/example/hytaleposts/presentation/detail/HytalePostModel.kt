package com.example.hytaleposts.presentation.detail

import com.example.hytaleposts.presentation.api.HytalePostResponse

sealed class HytalePostModel

data class HytalePostSuccess(val hytalePost: HytalePostResponse) : HytalePostModel()
object HytalePostLoader : HytalePostModel()
object HytalePostError : HytalePostModel()
