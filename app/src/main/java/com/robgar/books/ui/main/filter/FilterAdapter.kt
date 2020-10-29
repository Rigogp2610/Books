package com.robgar.books.ui.main.filter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robgar.books.R
import com.robgar.books.data.network.model.Book
import com.robgar.books.databinding.ItemFilterBooksBinding
import com.robgar.books.ui.inflate
import kotlin.properties.Delegates

class FilterAdapter(
    books: List<Book> = emptyList()
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var books: List<Book> by Delegates.observable(books) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_filter_books)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val context = view.context
        private var binding = ItemFilterBooksBinding.bind(view)

        fun bind(book: Book) {
            binding.tvTitle.text = "${context.getResources().getString(R.string.title)} ${book.title}"
            binding.tvIsbn.text = "${context.getResources().getString(R.string.isbn)} ${book.isbn}"
            binding.tvDescription.text = "${context.getResources().getString(R.string.description)} ${book.description}"
        }
    }
}