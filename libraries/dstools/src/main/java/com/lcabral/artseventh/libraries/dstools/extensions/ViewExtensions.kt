package com.lcabral.artseventh.libraries.dstools.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lcabral.artseventh.libraries.dstools.R

fun Fragment.showError() {
    val materialAlertDialogBuilder =
        MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.AlertDialog_AppCompat
        )
    materialAlertDialogBuilder
        .setTitle(getString(R.string.ds_dialog_title_feedback_error))
        .setMessage(getString(R.string.ds_dialog_feedback_message_error))
        .setIcon(R.drawable.ic_error)
        .setNegativeButton(getString(R.string.ds_dialog_icon_cancel)) { dialog, which ->
            showToast(getString(R.string.ds_dialog_feedback_error_canceled))
        }

    materialAlertDialogBuilder.show()
}

internal fun Fragment.showToast(string: String) {
    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
}