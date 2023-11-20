package com.lcabral.artseventh.features.toprated.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.toprated.R
import com.lcabral.artseventh.features.toprated.databinding.CustomBottomSheetTopRatedBinding
import com.lcabral.artseventh.features.toprated.databinding.FragmentTopRatedBinding
import com.lcabral.artseventh.features.toprated.presentation.adapter.TopRatedAdapter
import com.lcabral.artseventh.features.toprated.presentation.viewmodel.TopRatedAction
import com.lcabral.artseventh.libraries.dstools.extensions.showError
import com.lcabral.artseventh.features.toprated.presentation.viewmodel.TopRatedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class TopRatedFragment : Fragment(R.layout.fragment_top_rated) {
    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TopRatedViewModel by viewModel()
    private val topRatedAdapter by lazy { TopRatedAdapter { viewModel.onAdapterItemClicked(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTopRated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            updateList(state.getTopRatedResultItems)
            flipperContainerState(state.flipperChild)
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                TopRatedAction.ShowError -> showError()
                is TopRatedAction.GoToDetails -> gotToDetails(action.topRated)
            }
        }
    }

    private fun flipperContainerState(childFlipper: Int) {
        binding.flipperContainer.displayedChild = childFlipper
    }

    private fun updateList(topRatedList: List<Movie>?) {
        topRatedAdapter.submitList(topRatedList)
    }

    private fun setupRecyclerView() {
        binding.recyclerTopRated.apply {
            setHasFixedSize(true)
            adapter = topRatedAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun gotToDetails(movie: Movie) {
        showBottomSheet(movie)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("PrivateResource")
    private fun showBottomSheet(movie: Movie) {
        val dialog = BottomSheetDialog(
            requireContext(),
            com.google.android.material.R.style.Base_Theme_Material3_Dark_BottomSheetDialog
        )

        val bottomSheetBinding: CustomBottomSheetTopRatedBinding =
            CustomBottomSheetTopRatedBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(bottomSheetBinding.root)
        with(bottomSheetBinding) {
            imgDetails.load(getString(R.string.top_rated_uri_image) + movie.backdropPath)
            tvTitleMovieDetails.text = movie.name
            tvOverviewDetails.text = movie.overview
            tvReleaseDetails.text =
                String.format(getString(R.string.top_rated_release), movie.release)
            tvVoteCountDetails.text =
                String.format(getString(R.string.top_rated_vote_count), movie.voteCount)
            tvVoteAverageDetails.text = String.format(
                getString(R.string.top_rated_vote_average),
                movie.voteAverage
            )
            dialog.show()
            closeBtn.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    companion object {
        fun newInstance(): TopRatedFragment = TopRatedFragment()
    }
}
