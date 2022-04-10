package com.miso.showimage.Activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.miso.showimage.model.ImageDto
import com.miso.showimage.model.ImageRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: ImageRepository

    @Inject
    lateinit var imageListLiveData: MutableLiveData<List<ImageDto>>

    lateinit var pagingData: Flow<PagingData<ImageDto>>

    fun setupPagingData() {
        pagingData = repository.getPagingData().map {
            val imageDataMap = mutableSetOf<String>()
            it.filter { imageDto ->
                if (imageDataMap .contains(imageDto.id)) {
                    false
                } else {
                    imageDataMap.add(imageDto.id)
                }
            }
        }.
        cachedIn(viewModelScope)
    }

}