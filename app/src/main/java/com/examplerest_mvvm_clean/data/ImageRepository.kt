package com.examplerest_mvvm_clean.data

import com.examplerest_mvvm_clean.data.api.ImageApi
import com.examplerest_mvvm_clean.data.model.toImages
import com.examplerest_mvvm_clean.domain.model.Image
import com.examplerest_mvvm_clean.network.Output
import com.examplerest_mvvm_clean.network.parseResponse

class ImageRepositoryImpl(
    private val service: ImageApi
) : ImageRepository {

    override suspend fun getImagesCats(): List<Image> {
        return when (val result = service.getImagesCats().parseResponse()) {
            is Output.Success -> {
                val albunsResponseList = result.value.data

                val images: MutableList<Image> = ArrayList()
                if (albunsResponseList != null)
                    for (alb in albunsResponseList)
                        images.addAll(alb.toImages())

                return images
            }
            is Output.Failure -> throw GetImagesException()
        }
    }
}

interface ImageRepository {
    suspend fun getImagesCats(): List<Image>
}

class GetImagesException : Exception()
