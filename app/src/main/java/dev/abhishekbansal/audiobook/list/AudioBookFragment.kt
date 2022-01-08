package dev.abhishekbansal.audiobook.list

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.abhishekbansal.audiobook.R
import dev.abhishekbansal.audiobook.databinding.FragmentAudioBookBinding
import dev.abhishekbansal.audiobook.utils.photoloader.PhotoLoader
import org.koin.android.ext.android.inject


class AudioBookFragment : Fragment(), Observer<AudioBookUiState>, AudioBookAdapter.ItemClickListener {

    private var binding: FragmentAudioBookBinding? = null
    private val viewModel by inject<AudioBookViewModel>()
    private val photoLoader by inject<PhotoLoader>()
    private val adapter by lazy {
        AudioBookAdapter(photoLoader)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_audio_book, container, false)
        binding = FragmentAudioBookBinding.bind(rootView)

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter.itemClickListener = this
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState().observe(viewLifecycleOwner, this)
        viewModel.getBooks()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_grouping, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.groupItems(item.itemId)

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onChanged(state: AudioBookUiState) {
        when (state) {
            is ErrorState -> {
                showError()
            }
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

    private fun showError() {
        binding?.apply {
            errorLayout.isVisible = true
            recyclerView.isVisible = false
            progressBar.isVisible = false

            retryBtn.setOnClickListener { viewModel.getBooks() }
        }
    }

    override fun onItemClick(position: Int, item: AdapterData, view: View) {
        when (view.id) {
            R.id.shuffleBtn -> {
                (item as? Header)?.apply {
                    val newHeader = Header(name, books.shuffled(), expanded)
                    adapter.setItem(position, newHeader)
                }
            }

            else -> {
            }
        }
    }

}