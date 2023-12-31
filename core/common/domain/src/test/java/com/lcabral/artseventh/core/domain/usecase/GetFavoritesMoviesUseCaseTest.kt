package com.lcabral.artseventh.core.domain.usecase

import app.cash.turbine.test
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

internal class GetFavoritesMoviesUseCaseTest {
    private val repository: MovieRepository = mockk()
    private val subject = GetFavoritesMoviesUseCase(repository)

    @Test
    fun `getFavorite Should to get favorites movies when is invoked`() = runTest {

        // Given
        val expected = Stub.getMovies()

        every { repository.getAllFavorites() } returns flow { emit(expected) }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
            verify { repository.getAllFavorites() }
    }
}
