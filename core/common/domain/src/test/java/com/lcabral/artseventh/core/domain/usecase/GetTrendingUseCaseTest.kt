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
import kotlin.test.assertFailsWith
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)

internal class GetTrendingUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val subject = GetTrendingUseCase(repository)

    @Test
    fun `gGetTrending Should return movies getTrending`(): Unit = runBlocking {
        // Given
        val expectedResult = Stub.getMovies()

        every { repository.getTrendings() } returns flow { emit(expectedResult) }

        // When
        val result = subject()

        // Then
        result.test {
            verify { repository.getTrendings() }
            assertEquals(expectItem(), expectedResult)
            expectComplete()
        }
    }

    @Test
    fun `getTrending Should return exception when invoked trending movies`() = runBlocking {
        // Given
        val expectedError = Throwable("Error message")

        every { repository.getTrendings() } throws expectedError

        // When
        val result = assertFailsWith<Throwable> { subject() }

        // Then
        assertEquals(expectedError, result)
        verify { repository.getTrendings() }
    }
}
