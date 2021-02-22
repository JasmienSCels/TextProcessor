package com.example.data.model

import com.example.domain.model.WordFrequencyDM

fun WordFrequencyDM?.toLM(): WordLM = when (this) {
    null -> WordLM(
        title = "",
        freq = 0,
        isPrime = false,
        createdAt = 0
    )
    else -> WordLM(
        title = word ?: "",
        freq = frequency ?: 0,
        isPrime = isPrime ?: false
    )
}


fun WordLM?.toDM(): WordFrequencyDM = when (this) {
    null -> WordFrequencyDM(
        word = "Unknown",
        frequency = 0,
        isPrime = false
    )
    else -> WordFrequencyDM(
        word = title ,
        frequency = freq,
        isPrime = isPrime
    )
}