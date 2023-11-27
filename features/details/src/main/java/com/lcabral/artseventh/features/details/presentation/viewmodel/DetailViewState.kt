package com.lcabral.artseventh.features.details.presentation.viewmodel

internal data class DetailViewState(
    val isFavoriteChecked: Boolean = false,
    var isEmptyState: Boolean = false,
    val errorMessage: String? = "",
)


