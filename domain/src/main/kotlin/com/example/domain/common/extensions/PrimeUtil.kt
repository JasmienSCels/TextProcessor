package com.example.domain.common.extensions

internal fun Int.isPrime(): Boolean {
    if (this < 2) return false
    for (i in 2..this / 2) {
        if (this % i == 0) {
            return false
        }
    }
    return true
}
