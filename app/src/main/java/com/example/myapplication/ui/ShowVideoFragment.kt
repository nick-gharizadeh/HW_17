package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentShowPosterBinding
import com.example.myapplication.databinding.FragmentShowVideoBinding
import com.example.myapplication.ui.movieList.MovieRemoteViewModel
import com.example.myapplication.ui.movieList.movieId


class ShowVideoFragment : Fragment() {

    lateinit var binding: FragmentShowVideoBinding
    val viewModel : MovieRemoteViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowVideoBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getVideoOfMovie(movieId)
        binding.webview.webViewClient= WebViewClient()
        viewModel.videoOfMovie.observe(viewLifecycleOwner)
        {
            val link = "https://www.youtube.com/watch?v=${it.results.get(0).key}"
            binding.webview.settings.javaScriptEnabled = true
            binding.webview.apply {
                loadUrl(link)
            }
        }

}
}