package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.ui.movieList.ConnectionStatus
import com.example.myapplication.ui.movieList.MovieAdaptor
import com.example.myapplication.ui.movieList.MovieViewModel
import com.example.myapplication.ui.movieList.movieId
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val viewModel: MovieViewModel by activityViewModels()
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
        viewModel.connectionStatus.observe(viewLifecycleOwner) {
            if (it== ConnectionStatus.NotConnected)
            {
                Toast.makeText(context,"It seems you are not connected to the internet!", Toast.LENGTH_SHORT).show()
            }
        }
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