package com.lcabral.arch.lib.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.lcabral.artseventh.libraries.arch.extensions.IntentParams

inline fun <reified A: Activity> Context.createIntent(
    flags:Int? = null,
    params: IntentParams = {}
) = Intent(this, A::class.java)
    .apply(params)
    .also { intent ->
        flags?.let { intent.flags = it}
    }

fun Fragment.onBackPressedHandleFragment(
    onBack:(() -> Unit) = {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }
) {
    activity?.onBackPressedDispatcher?.addCallback(
        object :OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               onBack()
            }
        }
    )
}

fun FragmentActivity.onBackPressedHandleFragmentActivity(
    isEnabled: Boolean = true,
    onBackPressed: () -> Unit
) = onBackPressedDispatcher.addCallback(
    object : OnBackPressedCallback(isEnabled) {
        override fun handleOnBackPressed() = onBackPressed()
    }
)

fun Fragment.supportActivity(): AppCompatActivity? {
    return activity as? AppCompatActivity
}

fun Fragment.supportActionBar(): ActionBar? {
    return supportActivity()?.supportActionBar
}
