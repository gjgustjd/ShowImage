package com.miso.showimage.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.miso.showimage.R
import com.miso.showimage.databinding.ActivityMainBinding
import com.miso.showimage.model.ImageDto
import com.miso.showimage.model.ImageRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var edt_search: EditText
    lateinit var recycler_Images: RecyclerView

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
        lifecycleScope.launch {
            viewModel.setupPagingData()
            viewModel.pagingData.collectLatest {
                setupRecycler()
                (recycler_Images.adapter as PagingImageListAdapter).submitData(
                    it
                )
            }
        }
    }

    fun setupRecycler() {
        recycler_Images.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        recycler_Images.adapter = PagingImageListAdapter(this)
    }
}