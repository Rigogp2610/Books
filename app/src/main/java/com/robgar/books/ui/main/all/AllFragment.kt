package com.robgar.books.ui.main.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.robgar.books.data.Result
import com.robgar.books.data.network.model.Book
import com.robgar.books.databinding.FragmentAllBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllFragment : Fragment() {

    companion object {
        fun newInstance() = AllFragment()
    }

    private lateinit var binding: FragmentAllBinding
    private val viewModel: AllViewModel by viewModel()
    private var allAdapter = AllAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerAll.adapter = allAdapter

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.books.observe(activity!!, {
            it?.let { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.recyclerAll.visibility = View.GONE
                        binding.progressAll.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.recyclerAll.visibility = View.VISIBLE
                        binding.progressAll.visibility = View.GONE
                        result.data?.let { books ->
                            updateBooks(books)
                        }
                    }
                    is Result.Error -> {
                        binding.recyclerAll.visibility = View.VISIBLE
                        binding.progressAll.visibility = View.GONE
                        Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.getBooks()
    }

    private fun updateBooks(books: List<Book>) {
        allAdapter.books = books
    }
}