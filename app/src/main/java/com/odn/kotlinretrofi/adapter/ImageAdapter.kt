package com.odn.kotlinretrofi.adapter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide


import com.odn.kotlinretrofi.ConvertTime
import com.odn.kotlinretrofi.model.ImageMeme
import com.odn.kotlinretrofi.R

/**
 * Created by edgararana on 22/04/17.
 */

public class ImageAdapter(private val context: Context, private val imageMemeList: List<ImageMeme>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_LOAD = 0
    val TYPE_IMAGE = 1


    internal var loadMoreListener: OnLoadMoreListener? = null
    internal var isLoading = false
    internal var isMoreDataAvailable = true
    private var clickListener: ClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.item_image, parent, false)

        return ViewHolderImage(view)


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val imageMeme = imageMemeList[position]

        if (position >= itemCount - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true
            loadMoreListener!!.onLoadMore(position)

        }
        if (holder is ViewHolderImage) {
            val viewHolderImage = holder


            viewHolderImage.tvDesc.text = imageMemeList[position].desc
            viewHolderImage.tvCount.text = imageMemeList[position].vote!! + ""
            viewHolderImage.shareButton.setOnClickListener {
                // With android Marshmallow, the user grants permissions at runtime.
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(context as Activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            0)
                } else {

                    //String nameGif = imageMeme.getUrl().substring(40, imageMeme.getUrl().length());
                    val nameGif = imageMeme.url!!.substring(40, imageMeme.url!!.length)

                }
            }


            viewHolderImage.tvTime.text = ConvertTime.converteTimestamp(imageMemeList[position].timestamp)

            Glide.with(context).load(imageMeme.url).into(viewHolderImage.imageView)




            viewHolderImage.main.setOnClickListener {
                if (clickListener != null) {
                    // TODO: 24/04/17 si es andoid 5 en adelante se puede usar el compartimiento de imagenes
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


                    } else {
                        clickListener!!.itemClicked(position)

                    }

                }
            }
        }


    }


    override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {

        if (holder is ViewHolderImage) {
            val viewHolderVideo = holder as ViewHolderImage?
            super.onViewRecycled(viewHolderVideo)
        }

    }

    // TODO: 27/04/2017 holder de la iamgenes
    internal inner class ViewHolderImage(view: View) : RecyclerView.ViewHolder(view) {

        var main: FrameLayout
        var tvDesc: TextView
        var tvCount: TextView
        var tvTime: TextView
        var imageView: ImageView

        val shareButton: ImageView

        init {

            main = view.findViewById<View>(R.id.almf_root) as FrameLayout
            tvDesc = view.findViewById<View>(R.id.ii_text) as TextView
            tvCount = view.findViewById<View>(R.id.ii_text_acunt) as TextView
            tvTime = view.findViewById<View>(R.id.ii_text_time) as TextView
            shareButton = view.findViewById<View>(R.id.share) as ImageView
            imageView = view.findViewById<View>(R.id.almf_iv_image) as ImageView


        }
    }


    override fun getItemCount(): Int {
        return imageMemeList.size
    }


    // TODO: 27/04/2017 intaface para comincar que cargue mas elementos
    interface OnLoadMoreListener {
        fun onLoadMore(position: Int)

    }

    fun setMoreDataAvailable(moreDataAvailable: Boolean) {
        isMoreDataAvailable = moreDataAvailable
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }


    // TODO: 27/04/2017 inteface de comuniacion cuando haces click
    interface ClickListener {
        fun itemClicked(position: Int)
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }


    fun notifyDataChanged() {
        notifyDataSetChanged()
        isLoading = false
    }


}

