package com.knight.flix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.knight.flix.R
import kotlinx.android.synthetic.main.movie_detail_activity.*


class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)
        loadImage()
        setTitle()
    }

    private fun loadImage() {
        val imageUrl = intent.getStringExtra(MOVIE_IMAGE_URL)
        Glide.with(moviePoster.context)
            .load(imageUrl)
            .into(moviePoster)
    }

    private fun setTitle() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = intent.getStringExtra(MOVIE_TITLE)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        const val MOVIE_IMAGE_URL = "IMAGE_URL"
        const val MOVIE_TITLE = "MOVIE_TITLE"
    }
}
