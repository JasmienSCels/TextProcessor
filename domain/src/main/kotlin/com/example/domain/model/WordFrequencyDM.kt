package com.example.domain.model

data class WordFrequencyDM (
    val word: String,
    val frequency: Int,
    var isPrime: Boolean?
)