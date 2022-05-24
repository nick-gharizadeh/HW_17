package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.ui.movieList.MovieAdaptor
import com.example.myapplication.ui.movieList.MovieRemoteViewModel
import com.example.myapplication.ui.movieList.movieId


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val viewModel: MovieRemoteViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //📌 make adaptor for spinner
        val spinnerAdaptor = ArrayAdapter.createFromResource(
            requireContext(), R.array.languages,
            android.R.layout.simple_spinner_item
        )
        spinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.spinnerLanguage.adapter=spinnerAdaptor
        //📌 starting search ...
        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.editText?.text.toString().isNotBlank())
                viewModel.getSearchMovies(
                    binding.editTextSearch.editText?.text.toString(),
                    binding.checkBoxAdult.isChecked,
                    binding.spinnerLanguage.selectedItem.toString()
                )
            else
                binding.editTextSearch.editText?.setError("Please fill this field to continue")
        }
        val adapter = MovieAdaptor { goToDetailFragment() }
        val numberOfColumns = 2
        binding.recyclerViewSearch.setLayoutManager(GridLayoutManager(context, numberOfColumns))
        viewModel.searchMovieList.observe(viewLifecycleOwner)
        {
            binding.recyclerViewSearch.adapter = adapter
            adapter.submitList(it)
        }
    }

    fun goToDetailFragment() {
        findNavController().navigate(R.id.action_searchFragment_to_detailFragment)
        viewModel.getMovieDetail(movieId)

    }
}