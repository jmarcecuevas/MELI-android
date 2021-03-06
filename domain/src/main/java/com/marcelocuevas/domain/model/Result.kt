package com.marcelocuevas.domain.model

import java.io.IOException

sealed class Result<out T: Any> {

    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error(val message: IOException): Result<Nothing>()
}