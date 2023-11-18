package com.lcabral.artseventh.features.search.navigation

import android.content.Context
import com.lcabral.arch.lib.extensions.createIntent
import com.lcabral.artseventh.core.common.navigation.SearchNavigation
import com.lcabral.artseventh.features.search.SearchActivity

internal class SearchNavigationImpl : SearchNavigation {
    override fun create(context: Context) {
        context.startActivity(context.createIntent<SearchActivity>())
    }
}
