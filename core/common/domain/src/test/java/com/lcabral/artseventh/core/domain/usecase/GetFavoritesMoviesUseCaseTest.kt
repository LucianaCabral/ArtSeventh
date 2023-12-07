package com.lcabral.artseventh.core.domain.usecase

import app.cash.turbine.test
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
internal class GetFavoritesMoviesUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetFavoritesMoviesUseCase(repository)

    @Test
    fun `getFavorite Should to get favorites movies when is invoked`() = runBlocking {

        // Given
        val expected = Stub.getMovies()

        every { repository.getAllFavorites() } returns flow { emit(expected) }

        // When
        val result = subject()

        // Then
        result.test {
            verify { repository.getAllFavorites() }
            assertEquals(expectItem(), expected)
            expectComplete()
        }
    }
}
