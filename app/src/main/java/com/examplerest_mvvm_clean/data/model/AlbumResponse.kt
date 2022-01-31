package com.examplerest_mvvm_clean.data.model

import com.examplerest_mvvm_clean.domain.model.Image
import java.util.ArrayList

data class AlbumResponse(
    val id: String,
    val title: String?,
    val link: String?,
    val images: List<ImageResponse>?
)

fun AlbumResponse.toImages(): List<Image> {
    val images: MutableList<Image> = ArrayList()
    if (this.images != null)
        for (img in this.images)
            images.add(img.toImage())

    return images
}