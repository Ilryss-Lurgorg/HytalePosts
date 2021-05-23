package com.example.hytaleposts.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hytaleposts.presentation.Singletons
import com.example.hytaleposts.presentation.api.HytalePostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HytalePostViewModel(private val slug: String) : ViewModel() {

    val hytalePost: MutableLiveData<HytalePostModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        hytalePost.value = HytalePostLoader
        Singletons.hytalePostApi.getHytalePostDetailBySlug(this.slug).enqueue(object :
            Callback<HytalePostResponse> {
            override fun onResponse(call: Call<HytalePostResponse>, response: Response<HytalePostResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    val hytalePostResponse = response.body()!!
                    hytalePost.value = HytalePostSuccess(hytalePostResponse)
                } else {
                    hytalePost.value = HytalePostError
                }
            }

            override fun onFailure(call: Call<HytalePostResponse>, t: Throwable) {
                hytalePost.value = HytalePostError
            }
        })
    }
}