package com.odn.kotlinretrofi

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.odn.kotlinretrofi.api.ServiceApi
import com.odn.kotlinretrofi.binding.Retro
import com.odn.kotlinretrofi.database.model.DBCrearPedido
import com.odn.kotlinretrofi.database.model.DataBaseCrearPedidoHelper
import com.odn.kotlinretrofi.databinding.ActivityRetroBinding
import com.odn.kotlinretrofi.model.ImageMeme
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit

class RetroActivity : AppCompatActivity() {

    var binding: ActivityRetroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retro)

        binding!!.userAgeTextView.text = "HOLA "+"${DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO}"


        var serviceApi = ServiceApi.retrofit.create(ServiceApi::class.java)
        var call = serviceApi.image

        call.enqueue(object : retrofit2.Callback<List<ImageMeme>>{
            override fun onResponse(call: Call<List<ImageMeme>>?, response: Response<List<ImageMeme>>?) {

            }

            override fun onFailure(call: Call<List<ImageMeme>>?, t: Throwable?) {

            }
        })
    }
}
