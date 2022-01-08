package dev.abhishekbansal.audiobook.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.abhishekbansal.audiobook.R
import dev.abhishekbansal.audiobook.data.AudioBook
import dev.abhishekbansal.audiobook.databinding.ItemBookBinding
import dev.abhishekbansal.audiobook.databinding.ItemHeaderBinding

class AudioBookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHeaderBinding.bind(itemView)
        fun bind(data: AdapterData) {
            (data as Header).apply {
                binding.root.text = name
            }
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBookBinding.bind(itemView)
        fun bind(data: AdapterData) {
            (data as Book).apply {
                binding.root.text = book.primaryGenreName
            }
        }
    }
}

interface AdapterData
class Header(val name: String, val books: List<AudioBook>) : AdapterData
class Book(val book: AudioBook) : AdapterData