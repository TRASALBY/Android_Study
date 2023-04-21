package com.example.retrofit_error_handling.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_error_handling.R
import com.example.retrofit_error_handling.databinding.ItemBookBinding
import com.example.retrofit_error_handling.presentation.model.BookUiModel

class BookAdapter : ListAdapter<BookUiModel, BookAdapter.BookViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookViewHolder(parent)

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
    ) {
        private val binding = ItemBookBinding.bind(itemView)

        fun bind(book: BookUiModel){
            binding.book = book
        }
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookUiModel>() {
            override fun areItemsTheSame(oldItem: BookUiModel, newItem: BookUiModel) =
                oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(oldItem: BookUiModel, newItem: BookUiModel) =
                oldItem == newItem

        }
    }

}