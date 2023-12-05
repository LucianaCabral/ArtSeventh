package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.repository.MovieRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class DeleteFavoriteUseCaseTest {

    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = DeleteFavoriteUseCase(repository)

    @Test
    fun `deleteFavorite Should to delete movie favorite when is invoked`() = runBlocking {
        // Given
        coEvery { repository.deleteFavorite(any()) } just Runs

        // When
        subject(movie = mockk())

        //Then
        coVerify { repository.deleteFavorite(any()) }
    }
}
