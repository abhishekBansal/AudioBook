package dev.abhishekbansal.audiobook.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.abhishekbansal.audiobook.R
import dev.abhishekbansal.audiobook.databinding.FragmentAudioBookBinding


class AudioBookFragment : Fragment() {

    private var binding: FragmentAudioBookBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_audio_book, container, false)
        binding = FragmentAudioBookBinding.bind(rootView)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}