package com.knight.flix.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Configuration(
    val images: Images
)
