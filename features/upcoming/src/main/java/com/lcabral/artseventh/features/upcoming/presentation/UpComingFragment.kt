package com.lcabral.artseventh.features.upcoming.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.upcoming.R
import com.lcabral.artseventh.features.upcoming.databinding.FragmentUpComingBinding
import com.lcabral.artseventh.features.upcoming.presentation.adapter.UpcomingAdapter
import com.lcabral.artseventh.features.upcoming.presentation.viewmodel.UpcomingViewAction
import com.lcabral.artseventh.features.upcoming.presentation.viewmodel.UpcomingViewModel
import com.lcabral.artseventh.libraries.dstools.extensions.showError
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class UpComingFragment : Fragment(R.layout.fragment_up_coming) {

    private var _binding: FragmentUpComingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpcomingViewModel by viewModel()
    private val upcomingAdapter by lazy { UpcomingAdapter { viewModel.onAdapterItemClicked(it) } }
    private val detailsNavigation: DetailsNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpComingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        handleUpcoming()
    }

    private fun updateList(upcomingList: List<Movie>?) {
        upcomingAdapter.submitList(upcomingList)
    }

    private fun flipperContainerState(childFlipper: Int) {
        binding.flipperContainer.displayedChild = childFlipper
    }

    private fun handleUpcoming() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            updateList(state.getUpcomingResultItems)
            flipperContainerState(state.flipperChild)
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                UpcomingViewAction.ShowError -> showError()
                is UpcomingViewAction.GoToDetails -> goToDetails(action.movie)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerUpcoming.apply {
            setHasFixedSize(true)
            adapter = upcomingAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUpcoming()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToDetails(movie: Movie) {
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
                popularity = movie.popularity,
            )
        )
    }

    companion object {
        fun newInstance(): UpComingFragment = UpComingFragment()
    }
}
