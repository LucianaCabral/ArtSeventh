package com.lcabral.artseventh.features.search

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lcabral.artseventh.features.search.databinding.ActivitySearchDetailsBinding
import com.lcabral.artseventh.features.search.databinding.CustomBottomSheetBinding
import com.lcabral.artseventh.features.search.presentation.viewmodel.SearchDetailsAction
import com.lcabral.artseventh.features.search.presentation.viewmodel.SearchDetailsViewModel
import com.lcabral.artseventh.libraries.arch.extensions.onHandleBackPressedComponentActivity
import com.lcabral.artseventh.libraries.arch.extensions.parcelable
import com.lcabral.artseventh.libraries.arch.extensions.toStringAnswer
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARGS_MOVIE = "argsMovie"

internal class SearchDetailsActivity : AppCompatActivity(R.layout.activity_search_details) {

    private lateinit var binding: ActivitySearchDetailsBinding
    private val args: MovieArgs? by lazy { intent.parcelable(ARGS_MOVIE) }
    private val viewModel: SearchDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.BLACK

        initView()
        onActionListeners()
        setupListeners()
        onHandleBackPressed()
    }

    private fun onActionListeners() {
        viewModel.viewAction.observe(this) { action ->
            when (action) {
                SearchDetailsAction.GoToMoreDetails -> initBottomSheet()
                SearchDetailsAction.NavigateBack -> finish()
            }
        }
    }

    private fun onHandleBackPressed() {
        onHandleBackPressedComponentActivity { viewModel.onBackButtonPressed() }
    }

    private fun setupListeners() {
        binding.tvMoreDetails.setOnClickListener {
            viewModel.onMoreDetailsClicked()
        }
        binding.detailsToolbar.setNavigationOnClickListener {
            viewModel.onBackButtonPressed()
        }
    }

    private fun initView() {
        with(binding) {
            imgDetails.load("https://image.tmdb.org/t/p/w500${args?.posterPath}")
            tvNameDetails.text = args?.name
            tvOverviewDetails.text = args?.overview
            tvOriginalLanguageDetails.text =
                String.format(getString(R.string.search_original_language), args?.originalLanguage)
            tvDataReleaseDetails.text =
                String.format(getString(R.string.search_release), args?.release?.format("yyyy"))
            String.format(getString(R.string.search_popularity), args?.popularity)
            tvVoteAverageDetails.text =
                String.format(getString(R.string.search_vote_count), args?.voteCount)
            tvVoteCountDetails.text =
                String.format(getString(R.string.search_vote_average), args?.voteAverage)
        }
    }

    private fun initBottomSheet() {
        val dialog = BottomSheetDialog(
            this, com.google.android.material.R.style.
            Base_Theme_Material3_Dark_BottomSheetDialog)
        val bottomSheetBinding: CustomBottomSheetBinding =
            CustomBottomSheetBinding.inflate(layoutInflater, null, false)
        dialog.setContentView(bottomSheetBinding.root)
        with(bottomSheetBinding) {
            idMovie.text = args?.id.toString()
            tvAdultDetails.text =
                String.format(getString(R.string.search_adult), args?.adult?.toStringAnswer())
            tvVideo.text = String.format(getString(R.string.search_video), args?.video?.toStringAnswer())
            tvTitleMovieDetails.text = args?.name
            imgBackdropDetails.load(getString(R.string.details_uri_image) + args?.backdropPath)
            tvPopularityDetails.text =
                String.format(getString(R.string.search_popularity), args?.popularity)
            tvOriginalLanguageDetails.text =
                String.format(getString(R.string.search_original_language), args?.originalLanguage)
            tvOriginalTitleDetails.text = String.format(
                getString(R.string.search_original_title),
                args?.originalTitle
            )
            dialog.show()
        }
    }

    @Parcelize
    data class MovieArgs(
        val adult: Boolean,
        val backdropPath: String,
        val id: Int,
        val name: String,
        val originalTitle: String,
        val overview: String,
        val originalLanguage: String,
        val posterPath: String,
        val popularity: Double,
        val release: String,
        val voteCount: Int,
        val voteAverage: Double,
        val video: Boolean
    ) : Parcelable

    companion object {
        fun getIntent(context: Context, args: MovieArgs): Intent {
            return Intent(context, SearchDetailsActivity::class.java).apply {
                putExtra(ARGS_MOVIE, args)
            }
        }
    }
}
