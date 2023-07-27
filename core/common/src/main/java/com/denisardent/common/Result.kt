package com.denisardent.common

sealed class Result<T>

class PendingResult<T>: Result<T>()

sealed class FinalResult<T>: Result<T>()

class ErrorResult<T>(val exception: Exception): FinalResult<T>()

class SuccessResult<T>(val data: T): FinalResult<T>()