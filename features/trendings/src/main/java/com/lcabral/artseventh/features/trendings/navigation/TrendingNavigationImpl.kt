package com.lcabral.artseventh.features.trendings.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.TrendingNavigation
import com.lcabral.artseventh.features.trendings.presentation.presentation.TrendingFragment

internal class TrendingNavigationImpl: TrendingNavigation {
    override fun navigateToTrending(): Fragment = TrendingFragment.newInstance()
}
