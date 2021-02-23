package com.example.domain.common.extensions

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import junit.framework.Assert.assertEquals

@RunWith(MockitoJUnitRunner::class)
class PrimeUtilTests {

    @Test
    fun `should return true for prime numbers`() {
        val nums = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)

        nums.forEach {
            assertEquals(true, it.isPrime())
        }

    }

    @Test
    fun `should return false for non-prime numbers`() {
        val nums = listOf(1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30)

        nums.forEach {
            assertEquals(false, it.isPrime())
        }
    }
}