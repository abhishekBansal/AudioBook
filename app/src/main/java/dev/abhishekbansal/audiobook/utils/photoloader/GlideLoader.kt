package dev.abhishekbansal.audiobook.utils.photoloader

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideLoader: PhotoLoader {
    override fun load(url: String, view: ImageView) {
        Glide.with(view)
            .load(url)
            .into(view)
    }
}