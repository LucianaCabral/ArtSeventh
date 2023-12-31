package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.GetMovieUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.movies.R
import com.lcabral.artseventh.libraries.arch.test.utils.MainDispatcherMainTestRule
import com.lcabral.artseventh.libraries.arch.test.utils.MovieStub.getMovie
import com.lcabral.artseventh.libraries.arch.test.utils.MovieStub.pagingData
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class MovieViewModelTest {

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherMainTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMoviesUseCase: GetMovieUseCase = mockk()
    private val saveFavoriteUseCase: SaveFavoriteMovieUseCase = mockk()
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk()
    private val getFavoritesUseCase: GetFavoritesMoviesUseCase = mockk()

    private lateinit var subject: MovieViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun instantiateViewModel() {
        subject = MovieViewModel(
            getMoviesUseCase,
            saveFavoriteUseCase,
            deleteFavoriteUseCase,
            getFavoritesUseCase
        )
    }

    @Test
    fun `getMovies Should show movies When is invoked`() = runTest {
        // Given
        every { getMoviesUseCase() } returns flowOf(pagingData)

        // When
        instantiateViewModel()

        // Then
        subject.state.test {
            assertNotNull(awaitItem().movies)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getMovies Should handle error with right message When movies fails and throw NotFoundException`() =
        runTest {
            // Given
            val expectedError = Throwable("Error message")

            coEvery { getMoviesUseCase() } throws expectedError

            // When
            val result = assertFailsWith<Throwable> { instantiateViewModel() }

            // Then
            assertEquals(expectedError, result)
        }

    @Test
    fun `getFavoriteMovies Should add movie to favorites When it is not a favorite`() = runTest {
        // Given
        every { getMoviesUseCase() } returns mockk(relaxed = true)
        every { getFavoritesUseCase() } returns mockk(relaxed = true)
        instantiateViewModel()

        // When
        subject.getFavoriteMovies()

        // Then
        coVerify { getMoviesUseCase() }
    }

    @Test
    fun `onAdapterItemClicked Should emit expected action goToDetails`() = runTest {
        // Given
        val expectedAction = MovieViewAction.GoToDetails(getMovie())
        every { getMoviesUseCase() } returns mockk(relaxed = true)
        instantiateViewModel()

        // When
        subject.onAdapterItemClicked(R.id.movie_image, getMovie(), isFavorite = true)

        //Then
        subject.action.test {
            assertEquals(expectedAction, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        coVerify { getMoviesUseCase() }
    }


    @Test
    fun `saveFavorite Should add movie to favorites When it is not a favorite`() = runTest {
        // Given
        val movie = mockk<Movie>()
        every { getMoviesUseCase() } returns mockk(relaxed = true)
        every { getFavoritesUseCase() } returns mockk(relaxed = true)
        coEvery { saveFavoriteUseCase(movie) } just Runs
        instantiateViewModel()
        // When
        subject.onAdapterItemClicked(R.id.add_favorite_checkbox, getMovie(), isFavorite = true)
        saveFavoriteUseCase(movie)

        // Then
        subject.state.test {
            assertNotNull(awaitItem().movies)
            cancelAndConsumeRemainingEvents()
        }
        coVerify { saveFavoriteUseCase(movie) }
        coVerify(exactly = 0) { deleteFavoriteUseCase(movie) }
    }

    @Test
    fun `deleteFavorite Should remove movie to favorites When it is a favorite`() = runTest {
        // Given
        val movie = mockk<Movie>()
        every { getMoviesUseCase() } returns mockk(relaxed = true)
        every { getFavoritesUseCase() } returns mockk(relaxed = true)
        coEvery { deleteFavoriteUseCase(movie) } just Runs
        instantiateViewModel()

        // When
        subject.onAdapterItemClicked(R.id.add_favorite_checkbox, movie, isFavorite = false)
        deleteFavoriteUseCase(movie)

        subject.state.test {
            assertNotNull(awaitItem().movies)
            cancelAndConsumeRemainingEvents()
        }

        coVerify(exactly = 0) { saveFavoriteUseCase(movie) }
        coVerify { deleteFavoriteUseCase(movie) }
    }
}

