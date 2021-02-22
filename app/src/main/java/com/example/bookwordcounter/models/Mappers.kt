package com.example.bookwordcounter.models

import com.example.domain.model.WordFrequencyDM

internal fun List<WordFrequencyDM>.toUMI() = map { it.toUMI() }

private fun WordFrequencyDM?.toUMI(): WordUIM = when (this) {
    null -> WordUIM(
        word = "Unknown",
        frequency = 0,
        isPrime = false
    )
    else -> WordUIM(
        word = word ?: "Unknown",
        frequency = frequency?: 0,
        isPrime = isPrime ?: false
    )
}