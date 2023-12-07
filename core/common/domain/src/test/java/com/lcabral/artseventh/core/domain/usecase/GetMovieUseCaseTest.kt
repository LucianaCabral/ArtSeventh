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
internal class GetMovieUseCaseTest {

    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetMovieUseCase(repository)

    @Test
    fun `GetMovies Should return movies`() = runBlocking {
        // Given
        val result = Stub.getMovies()

        every { repository.getMovies() } returns flow { emit(result) }

        // When
        val movieResult = subject.invoke()

        // Then
        movieResult.test {
            verify { repository.getMovies() }
            assertEquals(expectItem(), result)
            expectComplete()
        }
    }

    @Test
    fun `GetMovies Should return exception when invoked movies`() = runBlocking {
        // Given
        val cause = Throwable()
        val expectedResult = cause::class

        every { repository.getMovies() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(expectedResult, expectError()::class)
        }
        verify { repository.getMovies() }
    }
}
