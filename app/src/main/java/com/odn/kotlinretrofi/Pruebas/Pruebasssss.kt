package com.odn.kotlinretrofi.Pruebas

import com.odn.kotlinretrofi.model.Prueba

import java.util.ArrayList

/**
 * Created by EDGAR ARANA on 28/09/2017.
 */

class Pruebasssss {
    internal var list: ArrayList<Prueba>?=null

    internal fun init() {
        list = ArrayList()

        for (i in 0..9) {
            list!!.add(Prueba("title", i.toString() + ""))
        }
    }
}
