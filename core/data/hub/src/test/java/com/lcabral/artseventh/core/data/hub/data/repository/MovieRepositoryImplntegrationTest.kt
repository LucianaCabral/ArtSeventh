package com.lcabral.artseventh.core.data.hub.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.lcabral.artseventh.core.data.hub.data.repository.MovieStub.movieResponse
import com.lcabral.artseventh.core.data.hub.repository.MovieRepositoryImpl
import com.lcabral.artseventh.core.data.hub.source.RemoteDataSourceImpl
import com.lcabral.artseventh.core.data.hub.commomTest.TestRuleRemote
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

class MovieRepositoryImplIntegrationTest {

    @OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val remoteRule = TestRuleRemote()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    //    val mainCoroutineRule = MainCoroutineTestRule()
    private lateinit var subject: MovieRepository


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        subject = MovieRepositoryImpl(
            remoteDataSource = RemoteDataSourceImpl(
                service = remoteRule.createTestService()
            ),
            localDataSource = remoteRule.createTestService()
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `getMovies Should validate flow data creation When invoked`() = runBlocking {
        // Given

        val expect = movieResponse
        remoteRule.successResponse { MOVIE_SUCCESS_RESPONSE }

        // When
        val result = subject.getMovies()

        // Then
        result.test {
            Assert.assertEquals(expect,expectItem())
            expectComplete()
        }
    }
}