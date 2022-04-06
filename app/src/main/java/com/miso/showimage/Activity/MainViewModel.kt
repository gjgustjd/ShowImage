package com.miso.showimage.Activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miso.showimage.model.ImageDto
import com.miso.showimage.model.ImageRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: ImageRepository

    @Inject
    lateinit var imageListLiveData: MutableLiveData<List<ImageDto>>

    fun getImageList(page: Int = 2, limit: Int = 100) {
        repository.getImageList(page, limit,
            { call, response ->
                if (response.isSuccessful()) {
                    imageListLiveData.value = response.body()
                } else {
                    Log.e("getImageList", response.errorBody().toString())
                }
            },
            { call, t ->
                Log.e("getImageList", t.stackTraceToString())
            })
    }
}