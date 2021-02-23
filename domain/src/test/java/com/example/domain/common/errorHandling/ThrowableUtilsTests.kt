package com.example.domain.common.errorHandling

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.ConnectException

@RunWith(MockitoJUnitRunner::class)
class ThrowableUtilsTests {

    @Test
    fun `should return true for Network Exception`() {
        val exception = ConnectException()
        assertEquals(true, exception.isConnectionError())
    }

    @Test
    fun `should return false for non-Network Exception`() {
        val exception = RuntimeException()
        assertEquals(false, exception.isConnectionError())
    }

}

