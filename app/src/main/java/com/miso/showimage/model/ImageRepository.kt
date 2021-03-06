package com.miso.showimage.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.GsonBuilder
import com.miso.showimage.Common.ShowImagePagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor() {
    suspend fun getImageList(
        page: Int = 0,
        limit: Int = 100
    ): List<ImageDto> {
        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(ImageAPI::class.java)
        return api.getImage(page, limit)
    }

    fun getPagingData(): Flow<PagingData<ImageDto>> {
        return Pager(
            PagingConfig(pageSize = 100)
        ) { ShowImagePagingSource(this) }.flow
    }

}