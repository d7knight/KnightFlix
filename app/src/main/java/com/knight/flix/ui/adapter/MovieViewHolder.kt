package com.knight.flix.ui.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.knight.flix.ui.MovieDetailActivity
import com.knight.flix.ui.model.Movie
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.movie_grid_item.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {
        val thumbnailRequest = Glide.with(itemView.context)
            .load(movie.thumbnailPosterUrl)
            .apply(RequestOptions().transform(BlurTransformation()))
        Glide.with(itemView.context)
            .load(movie.posterUrl)
            .thumbnail(thumbnailRequest)
            .into(itemView.posterImage)
        itemView.setOnClickListener {
            val intent =  Intent(itemView.context, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.MOVIE_IMAGE_URL, movie.posterUrl)
                putExtra(MovieDetailActivity.MOVIE_TITLE, movie.title)
            }
            itemView.context.startActivity( intent)
        }
    }
}