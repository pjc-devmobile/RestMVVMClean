package com.examplerest_mvvm_clean.presenter.model

import com.examplerest_mvvm_clean.domain.model.Image

class ImageUiModel(
    val link: String,
    val size: Int?
)

fun Image.toUiModel() = ImageUiModel(
    link = this.link,
    size = this.size
)

