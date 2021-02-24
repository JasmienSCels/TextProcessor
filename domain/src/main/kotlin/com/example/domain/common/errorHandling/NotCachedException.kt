package com.example.domain.common.errorHandling

import java.lang.RuntimeException

class NotCachedException(message:String): RuntimeException(message) {
}