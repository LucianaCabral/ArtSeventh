package com.lcabral.artseventh.features.toprated.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.TopRatedNavigation
import com.lcabral.artseventh.features.toprated.presentation.TopRatedFragment

internal class TopRatedNavigationImpl: TopRatedNavigation {
    override fun create(): Fragment = TopRatedFragment.newInstance()
}
