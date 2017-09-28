package com.odn.kotlinretrofi.api



import com.odn.kotlinretrofi.model.ImageMeme

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Created by edgararana on 24/04/17.
 */

interface ServiceApi {

    @get:GET("image_meme.php")
    val image: Call<List<ImageMeme>>

    companion object {
        // TODO: 08/11/2016  este objeto retrofit recibe la url general
        val retrofit = Retrofit.Builder()
                .baseUrl("http://360scripts.com.mx/train_v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


}
