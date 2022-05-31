package com.example.myapplication.ui.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.myapplication.R
import com.example.myapplication.databinding.MovieListItemViewBinding
import com.example.myapplication.model.Movie

var movieId= 0
typealias ClickHandler = ()-> Unit

class MovieAdaptor(private var clickHandler: ClickHandler) :
    ListAdapter<Movie, MovieAdaptor.ItemHolder>(MovieDiffCallback) {
    class ItemHolder(val binding: MovieListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .transform(CenterCrop())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.img)
                .into(poster)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding:MovieListItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_list_item_view,
            parent,false
        )
        return ItemHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.movie = movie
        holder.bind(movie)
        holder.binding.constraintLayout.setOnClickListener {
            movieId=getItem(position).id
            clickHandler.invoke()
        }
    }
}


object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}


