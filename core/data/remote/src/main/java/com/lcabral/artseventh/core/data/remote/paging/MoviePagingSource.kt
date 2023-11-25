package com.lcabral.artseventh.core.data.remote.paging

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lcabral.artseventh.core.data.remote.source.RemoteDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import java.time.LocalDateTime
import kotlin.math.max

const val LIMIT = 20
private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val movie = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = movie.id - (state.config.pageSize / 2))
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
        val range = page.until(page + params.loadSize)

        return LoadResult.Page(
            // TODO("data = Uma lista de itens buscados")
            data = range.map { number ->
                Movie(
                    id = number,
                    name = "SpiderMan $number",
                    posterPath = "jpj $number",
                    originalLanguage = "eng $number",
                    backdropPath = "jpg $number",
                    overview = "Lorem $number",
                    release = "2023 $number",
                    popularity = 9.0,
                    voteCount = 9,
                    voteAverage = 9.0,
                    adult = false,
                    originalTitle = "SpiderMan $number",
                    video = false
                )
            },
            prevKey = when(page) {
                STARTING_PAGE_INDEX -> null
                else -> page.dec()
            },
            nextKey = range.last + 1
        )
    }
    /** Verifica se a chave de paginação é válida:**/
    private fun ensureValidKey(key: Int) = max(STARTING_PAGE_INDEX,key)
}





