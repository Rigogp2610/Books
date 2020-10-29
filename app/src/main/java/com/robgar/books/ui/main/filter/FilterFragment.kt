package com.robgar.books.ui.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.robgar.books.R
import com.robgar.books.data.Result
import com.robgar.books.data.network.model.Book
import com.robgar.books.databinding.FragmentFilterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterFragment : Fragment() {

    companion object {
        fun newInstance(genre: String) = FilterFragment().apply {
            arguments = FilterViewModel.createArguments(genre)
        }
    }

    private lateinit var binding: FragmentFilterBinding
    private val viewModel: FilterViewModel by viewModel()
    private var filterAdapter = FilterAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setGenreTitle()

        binding.recyclerFilter.adapter = filterAdapter

        setupObserver()
    }

    private fun setGenreTitle() {
        binding.tvGenre.text = when (arguments?.getString(FilterViewModel.FILTER)) {
            resources.getString(R.string.fantasy_id) -> resources.getString(R.string.fantasy)
            resources.getString(R.string.crime_id) -> resources.getString(R.string.crime)
            resources.getString(R.string.romance_id) -> resources.getString(R.string.romance)
            else -> ""
        }.toUpperCase()
    }

    private fun setupObserver() {
        viewModel.books.observe(activity!!, {
            it?.let { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.recyclerFilter.visibility = View.GONE
                        binding.progressFilter.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.recyclerFilter.visibility = View.VISIBLE
                        binding.progressFilter.visibility = View.GONE
                        result.data?.let { books ->
                            updateBooks(books)
                        }
                    }
                    is Result.Error -> {
                        binding.recyclerFilter.visibility = View.VISIBLE
                        binding.progressFilter.visibility = View.GONE
                        Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.getBooks(arguments)
    }

    private fun updateBooks(books: List<Book>) {
        filterAdapter.books = books
    }

}