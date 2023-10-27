package com.lcabral.artseventh.features.upcoming.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.UpcomingNavigation
import com.lcabral.artseventh.features.upcoming.presentation.UpComingFragment

internal class UpcomingNavigationImpl: UpcomingNavigation {
    override fun navigateToUpcoming(): Fragment {
        return UpComingFragment.newInstance()
    }
}
