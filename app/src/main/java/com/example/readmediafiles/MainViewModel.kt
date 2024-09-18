package com.example.readmediafiles

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel (
    private val mediaReader: MediaReader
):ViewModel(){

    var files by mutableStateOf(listOf<MediaFile>())
        private set

    private val _uri: MutableStateFlow<Uri?> = MutableStateFlow<Uri?>(null)
    var uri: StateFlow<Uri?> = _uri

    fun selectUri(uri: Uri) {
        _uri.update {
            uri
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            files = mediaReader.getAllMediaFiles()
        }
    }
}