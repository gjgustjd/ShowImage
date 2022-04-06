package com.miso.showimage

import androidx.lifecycle.MutableLiveData
import com.miso.showimage.model.ImageDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class ShowImageModule {

    @Provides
    fun getImageListLiveData(): MutableLiveData<List<ImageDto>>
    {
        return MutableLiveData()
    }
}