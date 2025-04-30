package com.example.recipeapp.domain.util

import java.security.MessageDigest
import java.util.UUID

class Nonce {
    fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val message = MessageDigest.getInstance("SHA-256")
        val digest = message.digest(bytes)

        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}