package com.miso.showimage.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miso.showimage.R
import com.miso.showimage.databinding.ActivityMainBinding
import com.miso.showimage.model.ImageDto
import com.miso.showimage.model.ImageRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var edt_search: EditText
    lateinit var recycler_Images: RecyclerView
    lateinit var imageData: List<ImageDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeView()
        setupImageData()
    }

    fun initializeView() {
        edt_search = binding.edtAuthor
        recycler_Images = binding.recyclerImgs
    }

    fun setupImageData() {
        viewModel.getImageList()
        viewModel.imageListLiveData.observe(this, {
            imageData = it

            setupRecycler()
        })

    }

    fun setupRecycler() {
        recycler_Images.layoutManager = LinearLayoutManager(this)
        recycler_Images.adapter = RecyclerImageList(imageData)
    }
}