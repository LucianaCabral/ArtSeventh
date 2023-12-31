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
internal class GetTopRatedUseCaseTest {

    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetTopRatedUseCase(repository)

    @Test
    fun `getTopRated Should return movies TopRated`(): Unit = runBlocking {
        // Given
        val expectedResult = Stub.getMovies()

        every { repository.getTopRated() } returns flow { emit(expectedResult) }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(expectedResult, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getTopRated() }

    }

    @Test
    fun `getTopRated Should return exception when invoked topRated movies`() = runBlocking {
        // Given
        val cause = Throwable()

        every { repository.getTopRated() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(cause, awaitError())
        }
        verify { repository.getTopRated() }
    }
}
