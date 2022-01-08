package dev.abhishekbansal.audiobook.utils.photoloader

import android.widget.ImageView

interface PhotoLoader {
    fun load(url: String, view: ImageView)
}