package com.miso.showimage.Common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.miso.showimage.model.ImageDto
import com.miso.showimage.model.ImageRepository

class ShowImagePagingSource constructor(private val repository: ImageRepository) :
    PagingSource<Int, ImageDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDto> {
        return try {
            val next = params.key ?: 0
            val response = repository.getImageList(page = next)
            LoadResult.Page(
                data = response,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}