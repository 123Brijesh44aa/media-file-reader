package com.example.readmediafiles.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.readmediafiles.MainViewModel
import com.example.readmediafiles.MediaListItem

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: MainViewModel,
    navigateToDetail: (mediaUri: Uri) -> Unit
){
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        items(viewModel.files){
            MediaListItem(
                file = it,
                modifier = modifier.padding(8.dp)
                    .clickable {
                        navigateToDetail(it.uri)
                    }
            )
            Divider()
        }
    }
}