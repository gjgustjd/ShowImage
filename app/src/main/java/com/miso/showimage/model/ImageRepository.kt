package com.miso.showimage.model

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImageRepository {
    fun getImageList(page: Int = 2, limit: Int = 100) {
        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(ImageAPI::class.java)
        api.getImage(page, limit).enqueue(object : Callback<List<ImageDto>> {
            override fun onResponse(
                call: Call<List<ImageDto>>,
                response: Response<List<ImageDto>>
            ) {
                Log.i("getImageList", "onResponse")
            }

            override fun onFailure(call: Call<List<ImageDto>>, t: Throwable) {
                Log.i("getImageList", "onFailure")
            }
        }
        )
    }
}