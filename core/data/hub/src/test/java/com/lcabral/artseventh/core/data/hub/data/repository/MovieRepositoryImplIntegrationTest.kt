package com.lcabral.artseventh.core.data.hub.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingConfig
import app.cash.turbine.test
import com.lcabral.artseventh.core.data.hub.commomTest.TestRuleRemote
import com.lcabral.artseventh.core.data.hub.repository.MovieRepositoryImpl
import com.lcabral.artseventh.core.data.hub.source.RemoteDataSourceImpl
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryImplIntegrationTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val config: PagingConfig = mockk()

    @get:Rule
    val remoteRule = TestRuleRemote()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var subject: MovieRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        subject = MovieRepositoryImpl(
            remoteDataSource = RemoteDataSourceImpl(
                service = remoteRule.createTestService()
            ),
            localDataSource = remoteRule.createTestService(),
            config = config
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `getMovies Should validate flow data creation When invoked`() = runBlocking {

        // Given
        remoteRule.successResponse { MOVIE_SUCCESS_RESPONSE }

        // When
        val result = subject.getMovies()

        // Then
        result.test {
            assertNotNull(awaitEvent())
            cancelAndConsumeRemainingEvents()
        }
    }
}
