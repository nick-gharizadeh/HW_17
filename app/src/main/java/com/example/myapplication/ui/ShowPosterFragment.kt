package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.myapplication.databinding.FragmentShowPosterBinding
import com.example.myapplication.ui.movieList.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ShowPosterFragment : Fragment() {
    lateinit var binding: FragmentShowPosterBinding
    val viewModel : MovieViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowPosterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${viewModel.movieDetail.value?.poster_path}")
            .transform(CenterCrop())
            .into(binding.imageView)
    }
}