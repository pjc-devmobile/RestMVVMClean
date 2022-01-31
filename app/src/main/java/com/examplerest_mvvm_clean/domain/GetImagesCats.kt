package com.examplerest_mvvm_clean.domain

import com.examplerest_mvvm_clean.data.ImageRepository
import com.examplerest_mvvm_clean.domain.model.Image

class GetImagesCats(
    private val imageRepository: ImageRepository
) : GetImagesCatsUseCase {

    override suspend fun invoke(): List<Image> = imageRepository.getImagesCats()
}

interface GetImagesCatsUseCase {
    suspend operator fun invoke(): List<Image>
}