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

internal class GetUpcomingUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetUpcomingUseCase(repository)

    @Test
    fun `getUpcoming Should return getUpcoming movies`(): Unit = runBlocking {
        // Given
        val expectedResult = Stub.getMovies()

        every { repository.upcoming() } returns flow { emit(expectedResult) }

        // When
        val result = subject()

        // Then
        result.test {
            verify { repository.upcoming() }
            assertEquals(expectItem(), expectedResult)
            expectComplete()
        }
    }

    @Test
    fun `upcoming Should return exception when invoked upcoming movies`() = runBlocking {
        // Given
        val cause = Throwable()
        val expectedError = cause::class

        every { repository.upcoming() } returns flow { throw cause }

        // When
        val result = subject()

        // Then
        result.test {
            assertEquals(expectedError, expectError()::class)
        }
        verify { repository.upcoming() }
    }
}
