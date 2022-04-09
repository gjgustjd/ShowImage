package com.miso.showimage.Activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miso.showimage.databinding.ItemImageBinding
import com.miso.showimage.model.ImageDto

class RecyclerImageList(private val imageData: List<ImageDto>) :
    RecyclerView.Adapter<RecyclerImageList.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemImageBinding = ItemImageBinding.inflate(inflater, parent, false)
        return Holder(itemImageBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(imageData[position])
    }

    override fun getItemCount(): Int {
        return imageData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class Holder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageDto: ImageDto) {
            binding.imageDto = imageDto
        }
    }
}