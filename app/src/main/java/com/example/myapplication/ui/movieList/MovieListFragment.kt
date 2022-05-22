package com.example.myapplication.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMovieListBinding


class MovieListFragment : Fragment() {
    lateinit var binding: FragmentMovieListBinding
    val viewModel : MovieRemoteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_movie_list,container,false)
        binding.vModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

        viewModel.movieUpComingList.observe(viewLifecycleOwner) {
            binding.recyclerviewUpComing.adapter = adapterUpComing
            adapterUpComing.submitList(it)
        }
    }


    fun goToDetailFragment()
    {
        findNavController().navigate(R.id.action_movieListFragment_to_detailFragment)
    }


}