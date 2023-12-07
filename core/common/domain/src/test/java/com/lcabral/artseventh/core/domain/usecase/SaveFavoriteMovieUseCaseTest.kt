package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.repository.MovieRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class SaveFavoriteMovieUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = SaveFavoriteMovieUseCase(repository)

    @Test
    fun `saveFavorite Should to save favorites movies when is invoked`() = runBlocking {

        // Given
        coEvery { repository.addToFavorites(any()) } just Runs

        // When
        subject(movie = mockk())

        // Then
        coVerify { repository.addToFavorites(any()) }
    }
}
