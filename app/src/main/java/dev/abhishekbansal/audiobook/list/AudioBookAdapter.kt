package dev.abhishekbansal.audiobook.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.abhishekbansal.audiobook.R
import dev.abhishekbansal.audiobook.data.AudioBook
import dev.abhishekbansal.audiobook.databinding.ItemBookBinding
import dev.abhishekbansal.audiobook.databinding.ItemHeaderBinding
import dev.abhishekbansal.audiobook.utils.photoloader.PhotoLoader

class AudioBookAdapter(private val photoLoader: PhotoLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList = mutableListOf<AdapterData>()
    private val ParentItemType = 1
    private val ChildItemType = 2

    fun setItems(items: List<AdapterData>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ParentItemType) {
            val view = inflater.inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_book, parent, false)
            BookViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(itemList[position])
        } else if (holder is BookViewHolder) {
            holder.bind(itemList[position])
        }
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is Header -> ParentItemType
            is Book -> ChildItemType
            else -> ParentItemType
        }
    }

    private fun toggleItem(position: Int, header: Header) {
        if (!header.expanded) {
            val bookData = header.books.map { Book(it) }
            itemList.addAll(position + 1, bookData)
            notifyItemRangeInserted(position+1, bookData.size)
        } else {
            itemList.subList(position + 1, position + header.books.size + 1).clear()
            notifyItemRangeRemoved(position + 1, header.books.size)
        }

        header.expanded = !header.expanded
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHeaderBinding.bind(itemView)
        fun bind(data: AdapterData) {
            (data as Header).apply {
                binding.root.text = name
                binding.root.setOnClickListener {
                    toggleItem(adapterPosition, this)
                }
            }
        }
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBookBinding.bind(itemView)
        fun bind(data: AdapterData) {
            (data as Book).apply {
                binding.nameTv.text = book.collectionName
                binding.descriptionTv.text = book.description
                photoLoader.load(book.artworkUrl60, binding.imageView)
            }
        }
    }
}

interface AdapterData
class Header(val name: String, val books: List<AudioBook>, var expanded: Boolean = false) : AdapterData
class Book(val book: AudioBook) : AdapterData