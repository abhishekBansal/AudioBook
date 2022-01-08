package dev.abhishekbansal.audiobook.network

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptorProvider {
    fun provide() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}