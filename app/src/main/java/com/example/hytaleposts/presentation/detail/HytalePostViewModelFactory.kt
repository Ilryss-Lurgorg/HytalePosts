package com.example.hytaleposts.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class HytalePostViewModelFactory(private val slug: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HytalePostViewModel(slug) as T
    }
}