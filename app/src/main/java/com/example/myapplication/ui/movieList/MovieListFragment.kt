package com.example.myapplication.ui.movieList

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentMovieListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieListFragment : Fragment() {
    lateinit var binding: FragmentMovieListBinding
    val viewModel : MovieViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.searchMovieList.value= listOf()
        binding.buttonSearch2.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_searchFragment)
        }
        val adapter = MovieAdaptor { goToDetailFragment() }
        val adapterUpComing = MovieAdaptor{ goToDetailFragment() }
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.movieRecyclerView.setLayoutManager(layoutManager)
        binding.recyclerviewUpComing.setLayoutManager(layoutManager2)
        viewModel.movieList.observe(viewLifecycleOwner) {
            binding.movieRecyclerView.adapter = adapter
            adapter.submitList(it)
        }

        viewModel.allMovies?.observe(viewLifecycleOwner) {
            if(viewModel.connectionStatus.value==true)
            viewModel.getMovie()
        }
        viewModel.connectionStatus.observe(viewLifecycleOwner) {
            if (it)
            {
                Toast.makeText(context,"It seems you are not connected to the internet!",Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.movieUpComingList.observe(viewLifecycleOwner) {
            binding.recyclerviewUpComing.adapter = adapterUpComing
            adapterUpComing.submitList(it)
        }
    }

    fun goToDetailFragment()
    {
        findNavController().navigate(R.id.action_movieListFragment_to_detailFragment)
        viewModel.getMovieDetail(movieId)
    }


}