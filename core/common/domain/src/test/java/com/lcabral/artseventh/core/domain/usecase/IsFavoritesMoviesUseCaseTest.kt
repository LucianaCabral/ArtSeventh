package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

internal class IsFavoritesMoviesUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = IsFavoritesMoviesUseCase(repository)

    @Test
    fun `istFavoritesMovie Should to favorite movies when is invoked`() = runBlocking {
        // Given
        val movie = Stub.getMovie().id
        val expected = true

        coEvery { repository.isFavorite(movie) } returns true

        // When
        val result = subject(movie)

        // Then
        assertEquals(expected, result)
        coVerify { subject(movie) }
    }

    @Test
    fun `istFavoritesMovie Should  not to favorite movies when is invoked`() = runBlocking {
        // Given
        val movie = Stub.getMovie().id
        val expected = false

        coEvery { repository.isFavorite(movie) } returns expected

        // When
        val result = subject(movie)

        // Then
        assertEquals(expected, result)
        coVerify { subject(movie) }
    }
}
