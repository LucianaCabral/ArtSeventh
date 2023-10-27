package com.lcabral.artseventh.features.trendings.presentation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.trendings.R
import com.lcabral.artseventh.features.trendings.databinding.FragmentTrendingBinding
import com.lcabral.artseventh.features.trendings.presentation.adapter.TrendingAdapter
import com.lcabral.artseventh.features.trendings.presentation.presentation.viewmodel.TrendingViewModel
import com.lcabral.artseventh.libraries.dstools.extensions.showError
import com.lcabral.artseventh.features.trendings.presentation.presentation.viewmodel.ViewAction
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class TrendingFragment : Fragment(R.layout.fragment_trending) {

    private var _binding: FragmentTrendingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TrendingViewModel by viewModel()
    private val trendingAdapter by lazy { TrendingAdapter { viewModel.onAdapterItemClicked(it) } }
    private val detailsNavigation: DetailsNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTrendings()
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
            updateList(state.getTrendingsResultItems)
            flipperContainerState(state.flipperChild)
            onSuccessTrendingLoading(state.isLoading)
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                ViewAction.ShowError -> showError()
                is ViewAction.GoToDetails -> goToDetails(action.movie)
            }
        }
    }

    private fun flipperContainerState(childFlipper: Int) {
        binding.trendingList.displayedChild = childFlipper
    }

    private fun onSuccessTrendingLoading(isVisible: Boolean) {
        binding.progressCircular.isVisible = isVisible
    }

    private fun updateList(trendingList: List<Movie>?) {
        trendingAdapter.submitList(trendingList)
    }

    private fun setupRecyclerView() {
        binding.recyclerTrending.apply {
            setHasFixedSize(true)
            adapter = trendingAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
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
                popularity = movie.popularity)
        )
    }

    companion object {
        fun newInstance(): TrendingFragment = TrendingFragment()
    }
}
