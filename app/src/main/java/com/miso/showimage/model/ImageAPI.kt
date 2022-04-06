package com.miso.showimage.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageAPI {
    @GET("v2/list")
    fun getImage(@Query("page") page: Int = 2, @Query("limit") limit: Int = 100): Call<List<ImageDto>>
}