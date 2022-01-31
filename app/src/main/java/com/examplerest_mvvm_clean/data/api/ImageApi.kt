package com.examplerest_mvvm_clean.data.api

import com.examplerest_mvvm_clean.data.model.AlbumResponse
import com.examplerest_mvvm_clean.network.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {

    @GET("gallery/search/?q=cats&q_type=jpg")
    suspend fun getImagesCats(): Response<BaseResponse<AlbumResponse>>
}