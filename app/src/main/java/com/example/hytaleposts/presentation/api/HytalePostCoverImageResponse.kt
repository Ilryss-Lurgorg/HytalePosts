package com.example.hytaleposts.presentation.api

data class HytalePostCoverImageResponse(
    val variants: List<String>,
    val _id: String,
    val s3Key: String,
    val mimeType: String,
    val attached: Boolean,
    val contentType: String,
    val contentId: String,
    val createdAt: String,
    val __v: Integer
)