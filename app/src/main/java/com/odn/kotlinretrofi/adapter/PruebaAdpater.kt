package com.odn.kotlinretrofi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.odn.kotlinretrofi.R
import com.odn.kotlinretrofi.model.Prueba

/**
 * Created by EDGAR ARANA on 28/09/2017.
 */
class PruebaAdapter(var list: ArrayList<Prueba>, var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var clickListener: ClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.item_pruebas, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            val viewHolder = holder
            viewHolder.main.setOnClickListener(View.OnClickListener {
                clickListener!!.onClick(position)
            })
            viewHolder.tvTitle.setText(list.get(position).title)
            viewHolder.tvDesc.setText(list.get(position).description)

        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var main: FrameLayout
        var tvDesc: TextView
        var tvTitle: TextView

        init {
            main = view.findViewById<View>(R.id.root) as FrameLayout
            tvDesc = view.findViewById<View>(R.id.ip_tv_description) as TextView
            tvTitle = view.findViewById<View>(R.id.ip_tv_title) as TextView

        }

    }

    interface ClickListener {
        fun onClick(position: Int)
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }


}