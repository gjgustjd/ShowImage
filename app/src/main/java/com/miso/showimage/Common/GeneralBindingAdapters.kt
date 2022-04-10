package com.miso.showimage.Common

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

object GeneralBindingAdapters {
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .asBitmap()
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
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
                        imageView.setImageBitmap(resource)
                        return false
                    }
                })
                .into(imageView)
    }
}