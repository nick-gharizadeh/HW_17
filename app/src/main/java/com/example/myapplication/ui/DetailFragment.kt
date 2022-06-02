package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.ui.movieList.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    val viewModel: MovieViewModel by sharedViewModel()
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
        viewModel.movieDetail.observe(viewLifecycleOwner)
        {
            binding.movieTitle.text = it.title
            binding.movieReleaseDate.text = it.release_date
            binding.movieOverview.text = it.overview
            binding.movieRating.rating = it.vote_average.toFloat() / 2
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500/${it.poster_path}")
                .transform(CenterCrop())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.moviePoster)
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500/${it.backdrop_path}")
                .transform(CenterCrop())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.movieBackdrop)
        }
        binding.buttonVideo.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_showVideoFragment)
        }
        binding.buttonPoster.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_showPosterFragment)

        }
    }


}