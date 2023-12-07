package com.lcabral.artseventh.core.data.hub.data.repository

import com.lcabral.artseventh.core.domain.model.Movie

internal object MovieStub {
    val movieResponse = List(
        size = 1,
        init = {
            Movie(
                adult = false,
                backdropPath = "/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
                originalLanguage = "en",
                originalTitle = "Oppenheimer",
                overview = "A história do físico americano J. Robert Oppenheimer, seu papel no Projeto Manhattan e no desenvolvimento da bomba atômica durante a Segunda Guerra Mundial, e o quanto isso mudaria a história do mundo para sempre.",
                popularity = 2464.545,
                id = 872585,
                posterPath = "/c0DCmfC7Et2K3URnIJ4ahJpeXR2.jpg",
                release = "2023-07-19",
                name = "Oppenheimer",
                video = false,
                voteAverage = 8.211,
                voteCount = 4549

            )
        }
    )
}
