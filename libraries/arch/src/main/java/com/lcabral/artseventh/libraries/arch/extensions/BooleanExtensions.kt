package com.lcabral.artseventh.libraries.arch.extensions

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true
fun <T: Any> T?.isNull() = this == null
fun <T: Any> T?.isNotNull() = this != null
