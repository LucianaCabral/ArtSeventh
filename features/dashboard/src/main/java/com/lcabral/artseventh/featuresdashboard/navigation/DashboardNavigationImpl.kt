package com.lcabral.artseventh.featuresdashboard.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.DashboardNavigation
import com.lcabral.artseventh.featuresdashboard.presentation.DashboardFragment

internal class DashboardNavigationImpl : DashboardNavigation {
    override fun create(): Fragment = DashboardFragment.newInstance()
}
