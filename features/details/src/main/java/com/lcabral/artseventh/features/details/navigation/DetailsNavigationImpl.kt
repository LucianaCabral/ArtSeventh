package com.lcabral.artseventh.features.details.navigation

import android.content.Context
import com.lcabral.arch.lib.extensions.createIntent
import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.features.details.presentation.DetailsActivity

private const val ARGS_MOVIE = "argsMovie"

internal class DetailsNavigationImpl : DetailsNavigation {
    override fun showDetails(context: Context, args: MovieArgs) {
        context.startActivity(context.createIntent<DetailsActivity>().putExtra(ARGS_MOVIE, args))
    }
}
