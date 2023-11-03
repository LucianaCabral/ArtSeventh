package com.lcabral.artseventh.features.favorites

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.favorites.databinding.ActivityFavoriteBinding
import com.lcabral.artseventh.features.favorites.presentation.adapter.FavoriteAdapter
import com.lcabral.artseventh.features.favorites.presentation.viewmodel.FavoriteViewModel
import com.lcabral.artseventh.features.favorites.presentation.viewmodel.ViewAction
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FavoriteActivity : AppCompatActivity(R.layout.activity_favorite) {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private val favoriteAdapter by lazy { FavoriteAdapter {} }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.BLACK

        setupRecyclerView()
    }

    private fun setupObservables() {
        viewModel.viewState.observe(this) { state ->
            updateList(state.searchResultItems)
        }


            viewModel.viewAction.observe(this) { action ->
                when (action) {
                    is ViewAction.Go -> gotToDetails()
                    else -> {}
                }
            }
    }

    private fun updateList(favoritesList: List<Movie>?) {
        favoriteAdapter.submitList(favoritesList)
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerFavorite.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}