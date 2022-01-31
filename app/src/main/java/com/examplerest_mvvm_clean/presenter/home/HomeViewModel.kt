package com.examplerest_mvvm_clean.presenter.home

import androidx.lifecycle.*
import com.examplerest_mvvm_clean.domain.GetImagesCatsUseCase
import com.examplerest_mvvm_clean.presenter.model.ImageUiModel
import com.examplerest_mvvm_clean.presenter.model.toUiModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getImagesCatsUseCase: GetImagesCatsUseCase
) : ViewModel() {

    private val _images = MutableLiveData<List<ImageUiModel>>()
    val images = _images as LiveData<List<ImageUiModel>>

    val fetching = MutableLiveData(true)
    val error = MutableLiveData<String?>()

    fun getImages() {
        viewModelScope.launch {
            try {
                val imageList = getImagesCatsUseCase()
                _images.value = imageList.map { img ->
                    img.toUiModel()
                }
                fetching.value = false
            }catch (ex: Exception){
                error.value = ex.message
                fetching.value = false
            }
        }
    }
}

