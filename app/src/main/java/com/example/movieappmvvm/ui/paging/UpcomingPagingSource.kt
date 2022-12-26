package com.example.movieappmvvm.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieappmvvm.data.api.MoviesService
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.utils.CONSTANTS
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val DEFAULT_PAGE = 1

class UpcomingPagingSource @Inject constructor(
    private val networkApi : MoviesService,
) : PagingSource<Int , Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int , Movie> {

        val position = params.key ?: DEFAULT_PAGE

        return try {

            val response = networkApi.getUpcomingMovies(CONSTANTS.API_KEY, position)
            val data = response.body()!!.results

            LoadResult.Page(
                data = data!!,
                prevKey = if(position == DEFAULT_PAGE) null else position-1,
                nextKey = if(data!!.isEmpty()) null else position+1
            )

        }
        catch(exception : IOException) {
            LoadResult.Error(exception)
        }
        catch(exception : HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int , Movie>): Int {
        return 1
    }

}