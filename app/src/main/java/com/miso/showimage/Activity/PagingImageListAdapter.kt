package com.miso.showimage.Activity

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.miso.showimage.databinding.ItemImageBinding
import com.miso.showimage.model.ImageDto
import dagger.hilt.android.qualifiers.ActivityContext

class PagingImageListAdapter constructor(@ActivityContext private val context: Context) :
    PagingDataAdapter<ImageDto, PagingImageListAdapter.PagingViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagingViewHolder(
            ItemImageBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ImageDto>() {
            override fun areItemsTheSame(oldItem: ImageDto, newItem: ImageDto): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ImageDto, newItem: ImageDto): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class PagingViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageDto: ImageDto) {
            binding.imageDto = imageDto
        }
    }
}

