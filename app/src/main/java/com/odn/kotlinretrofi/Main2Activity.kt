package com.odn.kotlinretrofi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.odn.kotlinretrofi.adapter.PruebaAdapter
import com.odn.kotlinretrofi.database.model.DataBaseCrearPedidoHelper
import com.odn.kotlinretrofi.model.Prueba

class Main2Activity : AppCompatActivity(), PruebaAdapter.ClickListener {


    var heloWorl: TextView? = null

    var num: Int? = null

    var pruebaAdapter: PruebaAdapter? = null

    var list: ArrayList<Prueba>? = null

    var rvPruebas: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        if (intent.extras != null) {
            val userId = intent.getStringExtra("llave")
            Log.e("userId", userId)
        }
        heloWorl = findViewById(R.id.am2_tv_hello_world) as TextView
        rvPruebas = findViewById(R.id.am2_rv_pruebas) as RecyclerView
        num = 15
        heloWorl!!.setText(num.toString())
        heloWorl!!.text = "HOLA " +
                DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO +
                " sigie "
        var intent = Intent(this@Main2Activity, RetroActivity::class.java)
        startActivity(intent)
        list = ArrayList()


        for (i in 0..9) {
            list!!.add(Prueba("title", i.toString() + ""))

        }

        pruebaAdapter = PruebaAdapter(list!!, this@Main2Activity)

        pruebaAdapter!!.setClickListener(this)
        val mLayoutManager = LinearLayoutManager(this@Main2Activity)
        rvPruebas!!.layoutManager = mLayoutManager
        rvPruebas!!.itemAnimator = DefaultItemAnimator()
        rvPruebas!!.adapter = pruebaAdapter
        pruebaAdapter!!.notifyDataSetChanged()


    }

    override fun onClick(position: Int) {

    }


}
