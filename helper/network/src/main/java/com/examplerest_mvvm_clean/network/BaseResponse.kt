package com.examplerest_mvvm_clean.network

class BaseResponse<Data>(
    val data: List<Data>?,
    val status : Int,
    val success : Boolean
)