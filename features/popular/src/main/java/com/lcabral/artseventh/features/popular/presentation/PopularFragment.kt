package com.lcabral.artseventh.features.popular.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.popular.R
import com.lcabral.artseventh.features.popular.databinding.FragmentPopularBinding
import com.lcabral.artseventh.features.popular.presentation.adapter.PopularAdapter
import com.lcabral.artseventh.features.popular.presentation.viewmodel.PopularViewAction
import com.lcabral.artseventh.features.popular.presentation.viewmodel.PopularViewModel
import com.lcabral.artseventh.libraries.arch.extensions.showError
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class PopularFragment : Fragment(R.layout.fragment_popular) {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PopularViewModel by viewModel()
    private val popularAdapter by lazy { PopularAdapter { viewModel.onAdapterItemClicked(it) } }
    private val detailsNavigation: DetailsNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun flipperContainerState(childFlipper: Int) {
        binding.flipperConatiner.displayedChild = childFlipper
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
            updateList(state.getPopularResultItems)
            flipperContainerState(state.flipperChild)
            viewModel.viewAction.observe(viewLifecycleOwner) { action ->
                when (action) {
                    PopularViewAction.ShowError -> showError()
                    is PopularViewAction.GoToDetails -> navigateToDetails(action.popular)
                }
            }
        }
    }

    private fun updateList(movie: List<Movie>?) {
        popularAdapter.submitList(movie)
    }

    private fun setupRecyclerView() {
        binding.recyclerPopular.apply {
            setHasFixedSize(true)
            adapter = popularAdapter
        }
    }

    private fun navigateToDetails(movie: Movie) {
        detailsNavigation.showDetails(
            requireContext(),
            MovieArgs(
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
        fun newInstance(): PopularFragment = PopularFragment()
    }
}
