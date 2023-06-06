package com.denisardent.foodery.utils.security

interface SecurityUtils {
    fun generateSalt(): ByteArray

    fun passwordToHash(password: CharArray, salt: ByteArray): ByteArray

    fun bytesToString(bytes: ByteArray): String

    fun stringToBytes(string: String): ByteArray
}