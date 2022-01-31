package com.examplerest_mvvm_clean.domain.model

data class Image(
    val id: String,
    val title: String?,
    val description: String?,
    val datetime: Int?,
    val size : Int?,
    val views : Int?,
    val link : String
)