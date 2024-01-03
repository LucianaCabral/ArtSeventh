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
internal class GetPopularUseCaseTest {

    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetPopularUseCase(repository)

    @Test
    fun `GetPopular Should return popular When is invoked`() = runBlocking {
        // Given
        val expectedResult = Stub.getMovies()

        every { repository.getPopular() } returns flow { emit(expectedResult) }

        // When
        val result = subject.invoke()

        // Then
        result.test {
            assertEquals(expectedResult, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getPopular() }
    }

    @Test
    fun `GetPopular Should return exception when invoked popular`() = runBlocking {
        // Given
        val cause = Throwable()
        every { repository.getPopular() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(cause, awaitError())
        }
        verify { repository.getPopular() }
    }
}


