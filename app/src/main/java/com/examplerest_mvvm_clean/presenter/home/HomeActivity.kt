package com.examplerest_mvvm_clean.presenter.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.examplerest_mvvm_clean.presenter.model.ImageUiModel
import com.examplerest_mvvm_clean.theme.ExampleRest_MVVM_CleanTheme
import com.examplerest_mvvm_clean.theme.Red200
import org.koin.android.ext.android.inject
import java.util.ArrayList

class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleRest_MVVM_CleanTheme {
                val isLoading: Boolean by viewModel.fetching.observeAsState(
                    false
                )
                if (isLoading)
                    Loader()

                val error: String? by viewModel.error.observeAsState(null)
                if (error != null)
                    Error(viewModel.error.value!!)

                val images: List<ImageUiModel> by viewModel.images.observeAsState(
                    listOf()
                )
                if (!isLoading && images.isNotEmpty())
                    Images(images)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getImages()
    }

}

@Composable
fun Loader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator( )
    }
}

@Composable
fun Error(error: String) {
    Text(error, color = Red200)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Images(images: List<ImageUiModel>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(images.size) {
            Image(
                painter = rememberImagePainter(data = images.get(index = it).link),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(4.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExampleRest_MVVM_CleanTheme {
        Images(ArrayList())
    }
}