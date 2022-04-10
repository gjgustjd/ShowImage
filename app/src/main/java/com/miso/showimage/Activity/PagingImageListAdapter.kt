package com.miso.showimage.Activity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miso.showimage.databinding.ItemImageBinding
import com.miso.showimage.model.ImageDto
import dagger.hilt.android.qualifiers.ActivityContext

class PagingImageListAdapter constructor( @ActivityContext private  val context: Context) :
    PagingDataAdapter<ImageDto, PagingViewHolder>(diffCallback) {

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

        if (position <= itemCount) {
            val endPosition = if (position + 6 > itemCount) {
                itemCount
            } else {
                position + 6
            }

            for (i in position..endPosition-1) {
                preload(context, getItem(i)!!.download_url)
            }
        }
    }

    fun preload(context: Context, url: String) {
        Glide.with(context).load(url)
            .preload()
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
}

class PagingViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(imageDto: ImageDto) {
        binding.imageDto = imageDto
    }
}
