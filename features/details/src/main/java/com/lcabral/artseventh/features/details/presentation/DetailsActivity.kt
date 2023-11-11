package com.lcabral.artseventh.features.details.presentation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.lcabral.arch.lib.extensions.onBackPressedHandleFragmentActivity
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.features.details.R
import com.lcabral.artseventh.features.details.databinding.ActivityDetailsBinding
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailViewAction
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailsViewModel
import com.lcabral.artseventh.libraries.arch.extensions.orTrue
import com.lcabral.artseventh.libraries.arch.extensions.parcelable
import com.lcabral.artseventh.libraries.arch.extensions.toStringAnswer
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

private const val ARGS_MOVIE = "argsMovie"

internal class DetailsActivity : AppCompatActivity(R.layout.activity_details) {
    private lateinit var binding: ActivityDetailsBinding
    private val args: MovieArgs? by lazy { intent.extras?.parcelable(ARGS_MOVIE) }
    private val viewModel: DetailsViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.BLACK

        initViews()
        setupListeners()
        onHandleBackPressed()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.viewState.observe(this) { state ->
            binding.addFavoriteCheckbox.isChecked = state.isFavoriteChecked
            Log.d("<L>", "DetailsActivityFrom:${state.isFavoriteChecked} ")
        }

        viewModel.viewAction.observe(this) { action ->
            when (action) {
                DetailViewAction.NavigateBack -> finish()
                DetailViewAction.FavoriteChecked -> Toast.makeText(applicationContext, "itemClicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListeners() = with(binding) {
        detailsToolbar.setNavigationOnClickListener {
            viewModel.onBackPressed()
        }
    }

    private fun onHandleBackPressed() {
        onBackPressedHandleFragmentActivity { viewModel.onBackPressed() }
        supportFragmentManager.popBackStackImmediate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() = with(binding) {
        imageMovie.load(getString(R.string.details_image_uri) + args?.posterPath)
        val getLocalDate = LocalDate.parse(args?.release).year
        chipRelease.text = getLocalDate.toString()
        textTitle.text = args?.name
        includeDetails.tvOverviewDetails.text = args?.overview
        includeDetails.tvVoteCountDetails.text =
            String.format(getString(R.string.details_vote_count), args?.voteCount)
        includeDetails.tvVoteAverageDetails.text =
            String.format(getString(R.string.details_vote_average), args?.voteAverage)
        includeDetails.tvIdDetails.text = String.format(
            getString(R.string.details_movie_id), args?.id
        )

        includeDetails.tvPopularity.text = String.format(
            getString(R.string.details_popularity), args?.popularity
        )
        includeDetails.tvAdult.text = String.format(
            getString(R.string.details_adult),
            args?.adult?.toStringAnswer()
        )
        includeDetails.tvVideo.text = String.format(
            getString(R.string.details_video),
            args?.video?.toStringAnswer()
        )

        includeOrigin.textOriginNameTitle.text = String.format(
            getString(R.string.details_original_title), args?.originalTitle
        )
        includeOrigin.textOriginLanguage.text = String.format(
            getString(R.string.details_original_language), args?.originalLanguage
        )
        addFavoriteCheckbox.isChecked = args?.isFavorite.orTrue()
    }
}
