package com.lcabral.artseventh.featuresdashboard.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.lcabral.artseventh.featuresdashboard.presentation.DashboardFragment

internal fun DashboardFragment.includeChild(containerId: Int, fragment: Fragment) {
    childFragmentManager.commit { replace(containerId, fragment, fragment::class.java.simpleName) }
}
