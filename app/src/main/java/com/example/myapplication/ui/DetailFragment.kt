package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentMovieListBinding
import com.example.myapplication.ui.movieList.MovieListViewModel
import com.example.myapplication.ui.movieList.movieId


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    val viewModel : MovieListViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
//        binding = DataBindingUtil.inflate(inflater,
//            R.layout.fragment_detail,container,false)
//        binding.vModel = viewModel
//        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel.getMovieDetail(movieId)
        viewModel.movieDetail.observe(viewLifecycleOwner)
        {
            binding.movieTitle.text =it.title
            binding.movieReleaseDate.text =it.release_date
            binding.movieOverview.text =it.overview

                Glide.with(requireContext())
                    .load("https://image.tmdb.org/t/p/w500/${it.poster_path}")
                    .transform(CenterCrop())
                    .into(binding.moviePoster)
                Glide.with(requireContext())
                    .load("https://image.tmdb.org/t/p/w500/${it.backdrop_path}")
                    .transform(CenterCrop())
                    .into(binding.movieBackdrop)
            }
        }

    
}