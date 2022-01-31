package com.examplerest_mvvm_clean

import com.examplerest_mvvm_clean.data.ImageRepository
import com.examplerest_mvvm_clean.data.ImageRepositoryImpl
import com.examplerest_mvvm_clean.data.api.ImageApi
import com.examplerest_mvvm_clean.domain.GetImagesCats
import com.examplerest_mvvm_clean.domain.GetImagesCatsUseCase
import com.examplerest_mvvm_clean.network.Service
import com.examplerest_mvvm_clean.presenter.home.HomeViewModel
import org.koin.dsl.module

val mealServiceModule = module {

    single { Service().createService(ImageApi::class.java) }

    single<ImageRepository> { ImageRepositoryImpl(get()) }

    single<GetImagesCatsUseCase> { GetImagesCats(get()) }

    single { HomeViewModel(get()) }
}