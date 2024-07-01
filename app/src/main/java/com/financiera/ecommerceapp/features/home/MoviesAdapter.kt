package com.financiera.ecommerceapp.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.financiera.ecommerceapp.R
import com.financiera.ecommerceapp.core.utils.GlobalConstants.poster_path
import com.financiera.ecommerceapp.domain.models.movies.MovieModel

class MoviesAdapter(
    private val movies: List<MovieModel>,
    private val itemRecipeClickListener: OnRecipeClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    fun interface OnRecipeClickListener {
        fun onRecipeClick(movies: MovieModel, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.movieName.text = movie.title
        Glide.with(holder.movieImage)
            .load(poster_path + movie.image)
            .centerCrop()
            .into(holder.movieImage);


        holder.itemView.setOnClickListener {
            itemRecipeClickListener.onRecipeClick(movie, position)
        }

    }

    override fun getItemCount(): Int = movies.size

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieName: TextView = itemView.findViewById(R.id.titleText)
        val movieImage: ImageView = itemView.findViewById(R.id.image)

    }


}