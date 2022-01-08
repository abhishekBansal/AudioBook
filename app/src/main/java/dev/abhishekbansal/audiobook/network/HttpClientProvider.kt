package dev.abhishekbansal.audiobook.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpClientProvider {
    fun provide(
        loggingInterceptor: HttpLoggingInterceptor,
        isDebugMode: Boolean
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        // Logging
        if (isDebugMode) {
            builder.networkInterceptors().add(loggingInterceptor)
        }

        return builder.build()
    }
}