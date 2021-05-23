package com.example.hytaleposts.presentation.list

import com.example.hytaleposts.presentation.api.HytalePostResponse

sealed class HytalePostListModel

data class HytalePostListSuccess(val hytalePostList: List<HytalePostResponse>) : HytalePostListModel()
object HytalePostListLoader : HytalePostListModel()
object HytalePostListError : HytalePostListModel()