package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentShowVideoBinding
import com.example.myapplication.ui.movieList.MovieViewModel
import com.example.myapplication.ui.movieList.movieId
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@AndroidEntryPoint
class ShowVideoFragment : Fragment() {

    lateinit var binding: FragmentShowVideoBinding
    val viewModel: MovieViewModel by activityViewModels()
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
        binding.webview.canGoBack()
        binding.webview.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action === MotionEvent.ACTION_UP && binding.webview.canGoBack()) {
                binding.webview.goBack()
                return@OnKeyListener true
            }
            false
        })
}
}