package dev.abhishekbansal.audiobook.utils

import com.squareup.moshi.Moshi

object MoshiProvider {
    fun provide() = Moshi.Builder().build()
}