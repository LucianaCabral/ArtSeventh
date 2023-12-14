package com.lcabral.artseventh.core.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lcabral.artseventh.core.data.remote.model.toMovies
import com.lcabral.artseventh.core.data.remote.source.RemoteDataSource
import com.lcabral.artseventh.core.domain.model.Movie

class MoviePagingSource(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return runCatching {
            val page = params.key ?: 1
            val response = remoteDataSource.getMovies(page)
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovies(),
                prevKey = if (page == 1) null else page.dec(),
                nextKey = if (movies.isEmpty()) null else page.inc()
            )

        }.getOrElse {
            LoadResult.Error(throwable = it)
        }
    }

//    /** Verifica se a chave de paginação é válida:**/
//    private fun ensureValidKey(key: Int) = max(STARTING_PAGE_INDEX, key)

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}