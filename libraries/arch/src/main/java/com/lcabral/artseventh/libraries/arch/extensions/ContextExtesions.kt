package com.lcabral.artseventh.libraries.arch.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified A: Activity> Context.createIntent(
    flags:Int? = null,
    params: IntentParams = {}
) = Intent(this, A::class.java)
    .apply(params)
    .also { intent ->
        flags?.let { intent.flags = it}
}
