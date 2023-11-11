package com.lcabral.artseventh.features.favorites.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.FavoritesNavigation
import com.lcabral.artseventh.features.favorites.FavoriteFragment

internal class FavoritesNavigationImpl : FavoritesNavigation {
    override fun create(): Fragment = FavoriteFragment.newInstance()
}