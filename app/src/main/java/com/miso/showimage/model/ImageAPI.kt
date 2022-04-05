package com.miso.showimage.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageAPI {
    @GET("https://picsum.photos/v2/list?page={Page}&limit={Limit}")
    fun getImage(@Path("Page") page:Int=2,@Path("Limit") limit:Int=100): Call<ImageDto>
}