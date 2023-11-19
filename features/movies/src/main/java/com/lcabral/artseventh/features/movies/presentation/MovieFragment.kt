package com.lcabral.artseventh.features.movies.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.features.movies.R
import com.lcabral.artseventh.features.movies.databinding.FragmentMovieBinding
import com.lcabral.artseventh.features.movies.presentation.adapter.MovieAdapter
import com.lcabral.artseventh.features.movies.presentation.viewmodel.MovieViewAction
import com.lcabral.artseventh.features.movies.presentation.viewmodel.MovieViewModel
import com.lcabral.artseventh.libraries.arch.extensions.showError
import org.koin.android.ext.android.inject

internal class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModel()
    private val detailsNavigation: DetailsNavigation by inject()
    private val isFavoriteUseCase: IsFavoritesMoviesUseCase by inject()
    private val movieAdapter by lazy {
        MovieAdapter(isFavoriteUseCase, viewModel::onAdapterItemClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
//        movieAdapter.jo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            updateList(state.getMoviesResultItems)
            Log.d("<LMovie>", "setupObservers:${state.getMoviesResultItems} ")
            flipperContainerState(state.flipperChild)
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                MovieViewAction.ShowError -> showError()
                is MovieViewAction.GoToDetails -> goToMoviesDetails(action.movie)
            }
        }
    }

    private fun flipperContainerState(childFlipper: Int) {
        binding.flipperContainer.displayedChild = childFlipper
    }

    private fun updateList(movies: List<Movie>?) {
        movieAdapter.submitList(movies)
    }

    private fun setupRecyclerView() {
        binding.recyclerMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun goToMoviesDetails(movie: Movie) {
        detailsNavigation.showDetails(
            requireContext(), MovieArgs(
                id = movie.id,
                adult = movie.adult,
                backdropPath = movie.backdropPath,
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                name = movie.name,
                overview = movie.overview,
                posterPath = movie.posterPath,
                release = movie.release,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount,
                video = movie.video,
                popularity = movie.popularity
            )
        )
    }

    companion object {
        fun newInstance(): MovieFragment = MovieFragment()
    }
}
