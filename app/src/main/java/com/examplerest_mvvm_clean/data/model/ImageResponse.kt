package com.examplerest_mvvm_clean.data.model

import com.examplerest_mvvm_clean.domain.model.Image

data class ImageResponse(
    val id: String,
    val title: String?,
    val description: String?,
    val datetime: Int?,
    val size: Int?,
    val views: Int?,
    val link: String,
    val tags: List<String>?,
    val width: Int?,
    val height: Int?,
    val type: String?,
)

fun ImageResponse.toImage() = Image(
    id = id,
    title = title,
    description = description,
    datetime = datetime,
    size = size,
    views = views,
    link = link
)