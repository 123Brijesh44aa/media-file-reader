package com.example.readmediafiles.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.readmediafiles.MainViewModel

@Composable
fun DetailScreen(
    modifier: Modifier,
    viewModel: MainViewModel
){

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(
                viewModel.uri.value ?: "https://images.pexels.com/photos/28455224/pexels-photo-28455224/free-photo-of-stylish-woman-in-denim-bucket-hat-fashion-portrait.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
            )
            .size(Size.ORIGINAL)
            .build()
    )

    val state = painter.state

    if (state is AsyncImagePainter.State.Loading){
        CircularProgressIndicator()
    }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.padding(8.dp),
    )
}