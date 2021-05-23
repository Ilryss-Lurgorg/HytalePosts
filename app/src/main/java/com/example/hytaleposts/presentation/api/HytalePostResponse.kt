package com.example.hytaleposts.presentation.api

data class HytalePostResponse (
    val featured: Boolean,
    val tags: List<String>,
    val _id: String,
    val author: String,
    val title: String,
    val publishedAt: String,
    val coverImage: HytalePostCoverImageResponse,
    val createdAt: String,
    val slug: String,
    val publishedTo: List<String>,
    val bodyExcerpt: String,
    val body: String
)