package com.lcabral.artseventh.features.popular.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.PopularNavigation
import com.lcabral.artseventh.features.popular.presentation.PopularFragment

internal class PopularNavigationImpl : PopularNavigation {
    override fun navigateToPopular(): Fragment = PopularFragment.newInstance()
}
