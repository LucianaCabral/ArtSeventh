package com.lcabral.artseventh.features.favorites.navigation

import android.content.Context
import com.lcabral.artseventh.core.common.navigation.FavoritesNavigation
import com.lcabral.artseventh.features.favorites.FavoriteActivity
import com.lcabral.artseventh.libraries.arch.extensions.createIntent

internal class FavoritesNavigationImpl: FavoritesNavigation {
    override fun create(context: Context) {
        context.startActivity(context.createIntent<FavoriteActivity>())
    }
}