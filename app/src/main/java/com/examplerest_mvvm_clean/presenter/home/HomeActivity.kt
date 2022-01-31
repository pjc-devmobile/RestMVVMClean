package com.examplerest_mvvm_clean.presenter.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.examplerest_mvvm_clean.presenter.model.ImageUiModel
import com.examplerest_mvvm_clean.theme.ExampleRest_MVVM_CleanTheme
import com.examplerest_mvvm_clean.theme.Red200
import org.koin.android.ext.android.inject

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
    Box(
        contentAlignment = Alignment.Center, // you apply alignment to all children
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center) // or to a specific child
        )
    }
}

@Composable
fun Error(error: String) {
    Text(error, color = Red200)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Images(images: List<ImageUiModel>) {
    val context = LocalContext.current
    //val lblTitle = stringResource(id = R.string.lbl_title)

    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(images.size) {
            var image = images.get(index = it)
            Image(
                painter = rememberImagePainter(data = image.link),
                contentDescription = image.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(200.dp).height(200.dp).padding(4.dp)
                    .clickable {
                        Toast.makeText(context,  image.title, Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExampleRest_MVVM_CleanTheme {
        Loader()
    }
}