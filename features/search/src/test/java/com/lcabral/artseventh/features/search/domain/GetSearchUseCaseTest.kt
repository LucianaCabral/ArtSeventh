package com.lcabral.artseventh.features.search.domain

import app.cash.turbine.test
import com.lcabral.artseventh.features.search.domain.model.Search
import com.lcabral.artseventh.features.search.domain.repository.SearchRepository
import com.lcabral.artseventh.features.search.domain.usecase.GetSearchUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
internal class GetSearchUseCaseTest {
    private val searchRepository: SearchRepository = mockk(relaxed = true)
    private val subject = GetSearchUseCase(searchRepository)

    @Test
    fun `SearchMovies Should return movies`() = runBlocking {
        // Given
        val query = "query"
        val result = mockk<List<Search>>()

        every { searchRepository.searchMovies(any()) } returns flow { emit(result) }

        // When
        val searchMovieResult = subject.invoke(query)

        // Then
        searchMovieResult.test {
            verify { searchRepository.searchMovies(query) }
            assertEquals(expectItem(), result)
            expectComplete()
        }
    }
}
