package com.lcabral.artseventh.features.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailViewAction
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailViewState
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailsViewModel
import com.lcabral.artseventh.libraries.arch.test.utils.movieData
import com.lcabral.artseventh.libraries.arch.test.utils.moviesData
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.UUID

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailsViewModelTest {

    private val initialState = DetailViewState()
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase = mockk(relaxed = true)
    private val getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase = mockk(relaxed = true)
    private val isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase = mockk(relaxed = true)
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk(relaxed = true)
    private val args: MovieArgs = mockk(relaxed = true)
    private lateinit var subject: DetailsViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    private fun setupSubject() {
        subject = DetailsViewModel(
            saveFavoriteMovieUseCase = saveFavoriteMovieUseCase,
            getFavoritesMoviesUseCase = getFavoritesMoviesUseCase,
            isFavoritesMoviesUseCase = isFavoritesMoviesUseCase,
            deleteFavoriteUseCase = deleteFavoriteUseCase,
            args = args
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onBackPressed should return When is pressed`() = runBlocking {
        // Given
        mockkStatic(UUID::class)
        val expectedAction = DetailViewAction.NavigateBack
        setupSubject()

        // When
        subject.onBackPressed()

        //Then
        assertEquals(expectedAction, subject.viewAction.value)
    }

    @Test
    fun `onFavorite Should to save the favorite When is invoked `() = runTest {
        // Given
        coEvery { saveFavoriteMovieUseCase(movieData()) } just Runs
        setupSubject()

        // When
        subject.onFavorite(movieData(), isFavorite = true)

        coVerify { saveFavoriteMovieUseCase(movieData()) }
    }

    @Test
    fun `onFavorite Should to delete the favorite When is invoked`() = runTest {
        // Given
        coEvery { deleteFavoriteUseCase(movieData()) } just Runs
        setupSubject()

        // When
        subject.onFavorite(movieData(), isFavorite = false)

        coVerify { deleteFavoriteUseCase(movieData()) }
    }

    @Test
    fun `onGetFavorite Should to get the favorite When is invoked`() = runTest {
        // Given
        coEvery { getFavoritesMoviesUseCase() } returns flowOf(moviesData())
        setupSubject()

        // When
        subject.onGetFavorite()

        coVerify { getFavoritesMoviesUseCase() }
    }

    @Test
    fun `movieIsFavorite() Should to get the favorite When is invoked`() = runTest {
        // Given
        coEvery { isFavoritesMoviesUseCase(args.id) } returns true

        // When
        setupSubject()

        coVerify { isFavoritesMoviesUseCase(args.id) }
    }

    @Test
    fun `movieIsFavorite() Should to get the favorite When is invoked11`() = runTest {
        // Given
        val firstState = initialState.copy(isFavoriteChecked = false)
        val expectedState = firstState.copy(isFavoriteChecked = true)

        coEvery { isFavoritesMoviesUseCase(args.id) } returns true
        setupSubject()

        // When
        assertEquals(expectedState, subject.viewState.value)
    }
}

