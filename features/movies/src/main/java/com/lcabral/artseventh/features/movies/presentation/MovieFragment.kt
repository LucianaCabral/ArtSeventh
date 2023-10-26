package com.lcabral.artseventh.features.movies.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.movies.R
import com.lcabral.artseventh.features.movies.databinding.FragmentMovieBinding
import com.lcabral.artseventh.features.movies.presentation.adapter.MovieAdapter
import com.lcabral.artseventh.features.movies.presentation.viewmodel.MovieViewAction
import com.lcabral.artseventh.features.movies.presentation.viewmodel.MovieViewModel
import com.lcabral.artseventh.libraries.arch.extensions.showError

internal class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModel()
    private val movieAdapter by lazy { MovieAdapter { viewModel.onAdapterItemClicked(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    companion object {
        fun newInstance(): MovieFragment = MovieFragment()
    }
}
