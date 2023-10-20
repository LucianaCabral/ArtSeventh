package com.lcabral.artseventh.libraries.arch.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showError() {
    val materialAlertDialogBuilder =
        MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.AlertDialog_AppCompat
        )
    materialAlertDialogBuilder
        .setTitle("Opa! Ocorreu um erro")
        .setMessage("Aguarde")
        .setNegativeButton("Cancelar") { dialog, which ->
            showToast("cancelado")
        }
    materialAlertDialogBuilder.show()
}