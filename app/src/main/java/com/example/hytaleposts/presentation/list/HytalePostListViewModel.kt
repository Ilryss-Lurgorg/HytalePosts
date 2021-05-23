package com.example.hytaleposts.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hytaleposts.presentation.api.HytalePostResponse
import com.example.hytaleposts.presentation.Singletons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HytalePostListViewModel: ViewModel() {
    val hytaleListPost : MutableLiveData<HytalePostListModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        hytaleListPost.value = HytalePostListLoader
        Singletons.hytalePostApi.getHytalePostList().enqueue(object: Callback<List<HytalePostResponse>> {
            override fun onResponse(call: Call<List<HytalePostResponse>>, response: Response<List<HytalePostResponse>>) {
                if(response.isSuccessful && response.body() != null) {
                    val hytalePostList = response.body()
                    if (hytalePostList != null) {
                        hytaleListPost.value = HytalePostListSuccess(hytalePostList)
                    }
                } else {
                    HytalePostListError
                }
            }

            override fun onFailure(call: Call<List<HytalePostResponse>>, t: Throwable) {
                hytaleListPost.value = HytalePostListError
            }
        })
    }
}