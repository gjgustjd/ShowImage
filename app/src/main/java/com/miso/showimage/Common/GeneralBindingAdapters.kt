package com.miso.showimage.Common

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object GeneralBindingAdapters {
    var bitmaps = HashMap<String, Bitmap?>()

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        if (bitmaps.containsKey(url)) {
            imageView.setImageBitmap(bitmaps.get(url))
        } else {
            Glide.with(imageView.context)
                .asBitmap()
                .load(url)
                .thumbnail(0.3f)
                .fitCenter()
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        bitmaps.put(url,resource)
                        return false
                    }
                })
                .into(imageView)
        }
    }
}