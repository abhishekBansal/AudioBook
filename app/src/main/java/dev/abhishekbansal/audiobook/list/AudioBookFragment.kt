package dev.abhishekbansal.audiobook.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.`LinearLayoutCompat$InspectionCompanion`
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.abhishekbansal.audiobook.R
import dev.abhishekbansal.audiobook.databinding.FragmentAudioBookBinding
import dev.abhishekbansal.audiobook.utils.photoloader.PhotoLoader
import org.koin.android.ext.android.inject


class AudioBookFragment : Fragment(), Observer<AudioBookUiState> {

    private var binding: FragmentAudioBookBinding? = null
    private val viewModel by inject<AudioBookViewModel>()
    private val photoLoader by inject<PhotoLoader>()
    private val adapter by lazy {
        AudioBookAdapter(photoLoader)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_audio_book, container, false)
        binding = FragmentAudioBookBinding.bind(rootView)

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState().observe(viewLifecycleOwner, this)
        viewModel.getBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onChanged(state: AudioBookUiState) {
        when(state) {
            is ErrorState -> TODO()
            LoadingState -> {
                showLoader()
            }
            is SuccessState -> {
                showList()
                adapter.setItems(state.bookList)
            }
        }
    }

    private fun showList() {
        binding?.apply {
            errorLayout.isVisible = false
            recyclerView.isVisible = true
            progressBar.isVisible = false
        }
    }

    private fun showLoader() {
        binding?.apply {
            errorLayout.isVisible = false
            recyclerView.isVisible = false
            progressBar.isVisible = true
        }
    }

}