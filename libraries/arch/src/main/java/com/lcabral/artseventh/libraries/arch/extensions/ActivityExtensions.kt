package com.lcabral.artseventh.libraries.arch.extensions

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback

fun ComponentActivity.onHandleBackPressedComponentActivity(
    isEnabled: Boolean = true,
    onBackPressed: () -> Unit = { finish() }
) = onBackPressedDispatcher.addCallback(
    object : OnBackPressedCallback(isEnabled) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }
)