package com.lcabral.artseventh.features.search

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcabral.artseventh.features.search.databinding.ActivitySearchBinding
import com.lcabral.artseventh.features.search.domain.model.Search
import com.lcabral.artseventh.features.search.presentation.adapter.SearchAdapter
import com.lcabral.artseventh.features.search.presentation.viewmodel.SearchAction
import com.lcabral.artseventh.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val LAST_SEARCH_QUERY = "last_search_query"
const val DEFAULT_QUERY = ""

internal class SearchActivity : AppCompatActivity(R.layout.activity_search) {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val searchAdapter by lazy { SearchAdapter { viewModel.onAdapterItemClicked(search = it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.BLACK

        setupRecyclerView()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?:
        DEFAULT_QUERY

        searchInit(query)
        setupObservables()
    }

    private fun updateList(searchLists: List<Search>?) {
        searchAdapter.submitList(searchLists)
    }

    private fun setupObservables() {
        viewModel.viewState.observe(this) { state ->
            updateList(state.searchResultItems)
            onSuccessMoviesLoading(state.isLoading)
        }

        viewModel.viewAction.observe(this) { action ->
            when (action) {
                SearchAction.ShowError -> TODO()
                is SearchAction.GotoDetails -> gotToDetails(action.search)
                else -> {}
            }
        }
    }

    private fun gotToDetails(search: Search) {
        val intent = SearchDetailsActivity.getIntent(
            context = this, SearchDetailsActivity.MovieArgs(
                adult = search.adult,
                backdropPath = search.backdropPath,
                id = search.id,
                name = search.name,
                overview = search.overview,
                originalLanguage = search.originalLanguage,
                originalTitle = search.originalTitle,
                popularity = search.popularity,
                posterPath = search.posterPath,
                release = search.release,
                voteCount = search.voteCount,
                voteAverage = search.voteAverage,
                video = search.video
            )
        )
        startActivity(intent)

        println("<La> ${search.posterPath}, ${search.name}, ${search.release}," +
                "${search.popularity} ")

    }

    private fun setupRecyclerView() = with(binding) {
        recyclerSearchMovie.apply {
            setHasFixedSize(true)
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun searchInit(query: String) = with(binding) {
        edSearchMovie.setText(query)
        edSearchMovie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                updateSearchList()
                true
            } else {
                false
            }
        }

        edSearchMovie.setOnKeyListener { _, keyCode, event ->
            if (event.action == android.view.KeyEvent.ACTION_DOWN && keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                updateSearchList()
                true
            } else {
                false
            }
        }
    }

    private fun updateSearchList() = with(binding) {
        edSearchMovie.editableText.trim().let {
            if (it.isNotEmpty()) {
                searchQuery(it.toString())
            }
        }
    }

    private fun searchQuery(query: String) {
        viewModel.submittedSearch(query)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            LAST_SEARCH_QUERY,
            binding.edSearchMovie.editableText.trim().toString()
        )
    }

    private fun onSuccessMoviesLoading(isVisible: Boolean) {
        binding.progressbarSearch.isVisible = isVisible
    }
}
