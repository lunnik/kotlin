package com.odn.kotlinretrofi

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.odn.kotlinretrofi.adapter.ImageAdapter
import com.odn.kotlinretrofi.api.ServiceApi
import com.odn.kotlinretrofi.database.model.DBCrearPedido
import com.odn.kotlinretrofi.database.model.PedidosGuardados
import com.odn.kotlinretrofi.model.ImageMeme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<ImageMeme>>, ImageAdapter.ClickListener {

    /**
     * la asigacion ? en las variables quiere decir que pueden ser anulables o no
     *
     * asignacion !! significa que estas seguro que en este instante no es nulo la varibale
     * * */
    var imageAdapter: ImageAdapter? = null
    internal var rvImageTop: RecyclerView? = null
    var context: Context = this
    var dbCrearPedido: DBCrearPedido = DBCrearPedido(this)

    var prueba: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSetUp()
        conextion()
    }

    fun initSetUp() {
        dbCrearPedido.open()
        dbCrearPedido.insert(1, "gksodjao", "skddfnsklf", 1, "0")
        rvImageTop = findViewById(R.id.fi_rv_image_top) as RecyclerView?


        try {
            for (i in 0..dbCrearPedido.listPedidosGuardados.size - 1) {

                val pg = dbCrearPedido.listPedidosGuardados[i]

                Log.e("pg prueba", pg.maquina)
                Log.e("i", i.toString())
            }
        } catch(e: Exception) {
        }

    }

    override fun onFailure(call: Call<List<ImageMeme>>?, t: Throwable?) {
        Log.e("image", call.toString())

    }

    override fun onResponse(call: Call<List<ImageMeme>>?, response: Response<List<ImageMeme>>?) {
        if (response != null) {
            Log.e("url", response.body().get(0).url)
            Log.e("timestamp", response.body().get(0).timestamp)
        }

    }

    fun conextion() {
        val serviceApi = ServiceApi.retrofit.create(ServiceApi::class.java)
        var call: Call<List<ImageMeme>> = serviceApi.image

        call.enqueue(object : Callback<List<ImageMeme>> {
            override fun onResponse(call: Call<List<ImageMeme>>?, response: Response<List<ImageMeme>>?) {
                imageAdapter = ImageAdapter(this@MainActivity, response!!.body())
                initRv()

            }

            override fun onFailure(call: Call<List<ImageMeme>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

    fun initRv() {
        imageAdapter!!.setClickListener(this)
        val mLayoutManager = LinearLayoutManager(context)
        rvImageTop!!.layoutManager = mLayoutManager
        rvImageTop!!.itemAnimator = DefaultItemAnimator()
        rvImageTop!!.adapter = imageAdapter
        imageAdapter!!.notifyDataChanged()
    }

    override fun itemClicked(position: Int) {
        Log.e("position", position.toString())
    }

}

