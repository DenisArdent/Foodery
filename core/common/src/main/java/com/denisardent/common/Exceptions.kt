@file:Suppress("unused")

package com.denisardent.common

open class AppException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}

class ParseBackendException(
    cause: Throwable
): AppException(cause)

class ConnectionException(
    cause: Throwable
): AppException(cause)

class RequestException(
    code: Int,
    message: String
): AppException(
    "$code: $message"
)

class AuthException: AppException()