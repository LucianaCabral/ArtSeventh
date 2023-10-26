package com.lcabral.artseventh.features.search.navigation

import android.content.Context
import com.lcabral.artseventh.core.common.navigation.SearchNavigation
import com.lcabral.artseventh.features.search.SearchActivity
import com.lcabral.artseventh.libraries.arch.extensions.createIntent

internal class SearchNavigationImpl : SearchNavigation {
    override fun create(context: Context) {
        context.startActivity(context.createIntent<SearchActivity>())
    }
}
