package com.lcabral.artseventh.features.favorites.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.features.favorites.presentation.viewmodel.FavoriteViewAction
import com.lcabral.artseventh.features.favorites.presentation.viewmodel.FavoriteViewModel
import com.lcabral.artseventh.features.favorites.presentation.adapter.FavoriteAdapter
import com.lcabral.features.favorites.R
import com.lcabral.features.favorites.databinding.FragmentFavoriteBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()
    private val isFavoriteUseCase: IsFavoritesMoviesUseCase by inject()
    private val favoriteAdapter by lazy {
        FavoriteAdapter(isFavoriteUseCase) {
            id, movie ->
            viewModel.onAdapterItemClicked(id, movie)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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
            updateList(state.getFavoritesMovies)
            Log.d("<L>", "setupObserversFromFavoriteFragment:${state.getFavoritesMovies} ")
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                is FavoriteViewAction.FavoriteClicked -> removeMovieFromFavorite(action.movie)
            }
        }
    }

    private fun removeMovieFromFavorite(movie: Movie) {
        favoriteAdapter.removeItemRemoved(movie.id)
    }

    private fun updateList(movies: List<Movie>?) {
        favoriteAdapter.submitList(movies)
        Log.d("<L>", "updateListFromFavoriteFragment:${movies} ")

    }

    private fun setupRecyclerView() {
        binding.recyclerFavorite.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }
}