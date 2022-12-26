package com.example.movieappmvvm.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.utils.CONSTANTS
import java.io.IOException

class PopularPagingSource(
    private val apiService: MoviesService
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Movie> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPopularMovies(CONSTANTS.API_KEY,currentPage)
            val responseData = mutableListOf<Movie>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}
