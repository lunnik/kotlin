package com.odn.kotlinretrofi.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by EDGAR ARANA on 03/07/2017.
 */

class DataBaseCrearPedidoHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREAR_PEDIDO);

        onCreate(db)
    }

    val contextlist: SQLiteDatabase
        get() {
            val context = this.writableDatabase
            return context
        }

    companion object {

        // Table Name
        val TABLE_CREAR_PEDIDO = "CREAR_PEDIDO"

        // Table contactos
        val _ID = "id"
        val SALA = "sala_id"
        val MAQUINA = "maquina"
        val COMPONENTE = "componente"
        val ID_TECNICO = "tecnico_id"
        val ESTATUS = "estatus"
        val PEDIDO = "peiddo"

        // Database Information
        internal val DB_NAME = "CREAR_PEDIDO_ODN.DB"

        // database version
        internal val DB_VERSION = 1

        private val CREATE_TABLE = "create table ${TABLE_CREAR_PEDIDO}(" +
                "${_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${SALA} INTEGER," +
                "${MAQUINA}  TEXT," +
                "${COMPONENTE}   TEXT, " +
                "${ID_TECNICO}   INTEGER ," +
                "${ESTATUS}   INTEGER, " +
                "${PEDIDO}   TEXT );"
    }

}