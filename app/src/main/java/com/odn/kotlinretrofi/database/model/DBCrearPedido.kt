package com.odn.kotlinretrofi.database.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

import java.util.ArrayList

/**
 * Created by EDGAR ARANA on 03/07/2017.
 */

class DBCrearPedido(private val context: Context) {
    private var dbHelper: DataBaseCrearPedidoHelper? = null

    private var database: SQLiteDatabase? = null

    @Throws(SQLException::class)
    fun open(): DBCrearPedido {
        dbHelper = DataBaseCrearPedidoHelper(context)
        database = dbHelper!!.writableDatabase
        return this
    }

    fun close() {
        dbHelper!!.close()
    }

    // TODO: 26/12/2016  se agrega el id del tecnico directo desde el insert para no pedir lo cada que se quiera ihacer insert
    fun insert(sala: Int, maquina: String, componente: String, status: Int, pedido: String) {
        val contentValue = ContentValues()
        contentValue.put(DataBaseCrearPedidoHelper.SALA, sala)//1
        contentValue.put(DataBaseCrearPedidoHelper.MAQUINA, maquina)//2
        contentValue.put(DataBaseCrearPedidoHelper.COMPONENTE, componente)//3
        contentValue.put(DataBaseCrearPedidoHelper.ID_TECNICO, 15)//4
        contentValue.put(DataBaseCrearPedidoHelper.ESTATUS, status)//5
        contentValue.put(DataBaseCrearPedidoHelper.PEDIDO, pedido)//6
        database!!.insert(DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO, null, contentValue)
    }

    fun delete(_id: Long) {
        database!!.delete(DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO, DataBaseCrearPedidoHelper._ID + "=" + _id, null)
    }

    //Log.e("ohfoiwsgauio", cursor.getInt(4)+"");
    val listPedidosGuardados: ArrayList<PedidosGuardados> get() {

        val selectQuery = "SELECT  * FROM" +
                " ${DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO}"
        val db = dbHelper!!.contextlist
        val cursor = db.rawQuery(selectQuery, null)
        val FavList = ArrayList<PedidosGuardados>()
        if (cursor.moveToFirst()) {
            do {
                FavList.add(PedidosGuardados(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getString(6)
                ))
            } while (cursor.moveToNext())
        }
        return FavList
    }

    fun update(_id: Long, status: Int): Int {
        val contentValues = ContentValues()
        contentValues.put(DataBaseCrearPedidoHelper.ESTATUS, status)//

        val i = database!!.update(DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO, contentValues, DataBaseCrearPedidoHelper._ID + " = " + _id, null)
        return i
    }

    fun getStatus(id: Long): Int {
        val selectQuery = "SELECT  " +
                DataBaseCrearPedidoHelper.ESTATUS + " FROM " + DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO +
                " Where " + DataBaseCrearPedidoHelper._ID + " = " + id
        var status = 0
        val db = dbHelper!!.contextlist
        val cursor = db.rawQuery(selectQuery, null)
        val FavList = ArrayList<PedidosGuardados>()
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getInt(0)
            } while (cursor.moveToNext())
        }


        return status
    }

    // TODO: 09/06/2017 consulta para tarea en segundo planao para enviar automanticamente los pedidos
    fun getListBackGround(): ArrayList<PedidosGuardados> {
        val selectQuery = "SELECT  * FROM " +
                DataBaseCrearPedidoHelper.TABLE_CREAR_PEDIDO + " WHERE " + DataBaseCrearPedidoHelper.ESTATUS + " = 0"
        val db = dbHelper!!.contextlist
        val cursor = db.rawQuery(selectQuery, null)
        val FavList = ArrayList<PedidosGuardados>()
        if (cursor.moveToFirst()) {
            do {
                FavList.add(PedidosGuardados(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getString(6)
                ))
            } while (cursor.moveToNext())
        }

        //Log.e("ohfoiwsgauio", cursor.getInt(4)+"");
        return FavList
    }

}