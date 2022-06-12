package com.baz.fir.util

sealed class ErrorType(val throwable: Throwable? = null) {

    data class GenericError(val error: Throwable) : ErrorType(error)

    object FileError : ErrorType()

    object ContentUnavailable : ErrorType()
}
