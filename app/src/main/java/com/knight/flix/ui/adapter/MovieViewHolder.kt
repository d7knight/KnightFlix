package com.knight.flix.ui.adapter

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.knight.flix.R
import com.knight.flix.ui.MovieDetailActivity
import com.knight.flix.ui.model.Movie
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.movie_grid_item.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val circularProgressDrawable by lazy(LazyThreadSafetyMode.NONE) {
        CircularProgressDrawable(itemView.context).apply {
            setColorSchemeColors(ContextCompat.getColor(itemView.context, R.color.red))
            strokeWidth = itemView.context.resources.getDimension(R.dimen.progress_stroke_width)
            centerRadius = itemView.context.resources.getDimension(R.dimen.progress_stroke_radius)
        }
    }

    fun bind(movie: Movie) {
        val thumbnailRequest = Glide.with(itemView.context)
            .load(movie.thumbnailPosterUrl)
            .apply(RequestOptions().transform(BlurTransformation()))
        Glide.with(itemView.context)
            .load(movie.posterUrl)
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .thumbnail(thumbnailRequest)
            .into(itemView.posterImage)
        circularProgressDrawable.start()
        itemView.setOnClickListener {
            val intent =  Intent(itemView.context, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.MOVIE_IMAGE_URL, movie.posterUrl)
                putExtra(MovieDetailActivity.MOVIE_TITLE, movie.title)
            }
            itemView.context.startActivity( intent)
        }
    }
}