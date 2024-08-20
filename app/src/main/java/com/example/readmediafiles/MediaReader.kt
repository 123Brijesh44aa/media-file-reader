package com.example.readmediafiles

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore

class MediaReader(
    private val context: Context
) {
    fun getAllMediaFiles(): List<MediaFile>{
        val mediaFiles = mutableListOf<MediaFile>()

        val queryUri = if (Build.VERSION.SDK_INT >= 29){
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else MediaStore.Files.getContentUri("external")

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE,
        )

        context.contentResolver.query(
            queryUri,
            projection,
            null,
            null,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Files.FileColumns._ID
            )
            val nameColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Files.FileColumns.DISPLAY_NAME
            )
            val mimeColumn = cursor.getColumnIndexOrThrow(
                MediaStore.Files.FileColumns.MIME_TYPE
            )

            while (cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val mimeType = cursor.getString(mimeColumn)

                if (name != null && mimeType != null) {

                    val contentUri = ContentUris.withAppendedId( // this line of code explains how to get the uri of the media file
                        // and we can use this uri to get the information of the media file
                        queryUri,
                        id
                    )

                    val mediaType = when {
                        mimeType.startsWith("audio/") -> MediaType.AUDIO
                        mimeType.startsWith("video/") -> MediaType.VIDEO
                        else -> MediaType.IMAGE
                    }

                    mediaFiles.add(
                        MediaFile(
                            uri = contentUri,
                            name = name,
                            type = mediaType,
                        )
                    )
                }
            }
        }

        return mediaFiles.toList()
    }
}