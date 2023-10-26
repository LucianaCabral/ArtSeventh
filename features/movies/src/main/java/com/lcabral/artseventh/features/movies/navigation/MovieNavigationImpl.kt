package com.lcabral.artseventh.features.movies.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.MovieNavigation
import com.lcabral.artseventh.features.movies.presentation.MovieFragment

internal class MovieNavigationImpl: MovieNavigation {
    override fun create(): Fragment = MovieFragment.newInstance()
}
