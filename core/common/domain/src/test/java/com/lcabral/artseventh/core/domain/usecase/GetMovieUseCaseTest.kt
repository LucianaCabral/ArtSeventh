package com.lcabral.artseventh.core.domain.usecase

import app.cash.turbine.test
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import com.lcabral.artseventh.core.domain.usecase.Stub.pagingData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
internal class GetMovieUseCaseTest {

    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetMovieUseCase(repository)

    @Test
    fun `GetMovies Should return movies`() = runBlocking {
        // Given
        val result = pagingData

        every { repository.getMovies() } returns flow { emit(result) }

        // When
        val movieResult = subject.invoke()

        // Then
        movieResult.test {
            assertEquals(result, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getMovies() }

    }

    @Test
    fun `GetMovies Should return exception when invoked movies`() = runTest {
        // Given
        val cause = Throwable()
        every { repository.getMovies() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(cause, awaitError())
        }
        verify { repository.getMovies() }
    }
}
