package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.lifecycle.Observer
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.GetMovieUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class MovieViewModelTest {

    @get:Rule
    var dispatcherRule = DispatcherTestRule()

    private val initialState = MovieStateView()

    private val getMoviesUseCase: GetMovieUseCase = mockk()
    private val saveFavoriteUseCase: SaveFavoriteMovieUseCase = mockk()
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk()
    private val getFavoritesUseCase: GetFavoritesMoviesUseCase = mockk()

    private lateinit var subject: MovieViewModel
    private val movieObserver: Observer<List<Movie>> = mockk(relaxed = true)
    private val movies = MovieStub.getMovies()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this, relaxed = true)
        instantiateViewModel()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun instantiateViewModel(): MovieViewModel {
        subject = MovieViewModel(
            getMoviesUseCase,
            saveFavoriteUseCase,
            deleteFavoriteUseCase,
            getFavoritesUseCase
        )
        movieObserver.onChanged(movies)
        return subject
    }

    @Test
    fun `init viewModel getMovies get success then sets Movies LiveData`() = runBlocking {
        // Given
        coEvery { getMoviesUseCase() } returns flow { emit(movies) }

        // When
        instantiateViewModel()

        // Then
        verify { instantiateViewModel().viewState.value }
    }

    @Test
    fun `getMovies show list movies`() = runBlocking {
        // Given
        coEvery { getMoviesUseCase() } returns flow { emit(movies) }

        //When
        instantiateViewModel()

        //Then
        verify { movieObserver.onChanged(movies) }
    }

    @Test
    fun `getMovies Should call observer`() {
        // Given
        clearMocks(movieObserver)
        coEvery { getMoviesUseCase.invoke() } returns flow { emit(movies) }

        //When
        instantiateViewModel()

        //Then
        coVerify { movieObserver.onChanged(movies) }
    }
}
