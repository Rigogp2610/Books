package com.robgar.books.ui.main.all

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robgar.books.R
import com.robgar.books.data.network.model.Book
import com.robgar.books.databinding.ItemAllBooksBinding
import com.robgar.books.ui.inflate
import kotlin.properties.Delegates

class AllAdapter(
    books: List<Book> = emptyList()
) : RecyclerView.Adapter<AllAdapter.ViewHolder>() {

    var books: List<Book> by Delegates.observable(books) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_all_books)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val context = view.context
        private var binding = ItemAllBooksBinding.bind(view)

        fun bind(book: Book) {
            binding.tvTitle.text = "${context.getResources().getString(R.string.title)} ${book.title}"
            binding.tvIsbn.text = "${context.getResources().getString(R.string.isbn)} ${book.isbn}"
            binding.tvDescription.text = "${context.getResources().getString(R.string.description)} ${book.description}"
            binding.tvGenre.text = "${context.getResources().getString(R.string.genre)} ${book.genre}"
        }
    }
}