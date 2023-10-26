package com.lcabral.artseventh.header.navigation

import androidx.fragment.app.Fragment
import com.lcabral.artseventh.core.common.navigation.HeaderNavigation
import com.lcabral.artseventh.header.presentation.HeaderFragment

internal class HeaderNavigationImpl : HeaderNavigation {
    override fun create(): Fragment = HeaderFragment.newInstance()
}
