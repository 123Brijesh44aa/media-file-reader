package com.example.readmediafiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MediaListItem(
    file: MediaFile,
    modifier: Modifier = Modifier
) {
    when (file.type) {
        MediaType.IMAGE -> {
            AsyncImage(
                model = file.uri,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Crop
            )

        }

        MediaType.VIDEO -> {
            Image(
                painter = painterResource(id = R.drawable.baseline_video_file_24),
                contentDescription = null,
                modifier = modifier.width(100.dp)
            )
        }

        MediaType.AUDIO -> {
            Image(
                painter = painterResource(id = R.drawable.baseline_audio_file_24),
                contentDescription = null,
                modifier = modifier.width(100.dp)
            )
        }
    }
}