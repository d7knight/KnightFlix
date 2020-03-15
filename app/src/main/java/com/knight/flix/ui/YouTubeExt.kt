package com.knight.flix.ui

import android.content.Context
import android.content.Intent
import android.net.Uri

internal fun Context.watchYoutubeVideos(query: String) {
    val webIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://www.youtube.com/results?search_query=$query")
    )
    startActivity(webIntent)
}