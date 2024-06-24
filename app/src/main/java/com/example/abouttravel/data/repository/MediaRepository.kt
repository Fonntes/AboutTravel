package com.example.abouttravel.data.repository

import androidx.lifecycle.LiveData
import com.example.abouttravel.data.dao.MediaDao
import com.example.abouttravel.data.entities.Media

class MediaRepository(private val mediaDao: MediaDao): BaseRepository() {

    val allMedia: LiveData<List<Media>> = mediaDao.getAllMedias()

    suspend fun insert(media: Media) {
        mediaDao.insertMedia(media)
    }

    suspend fun update(media: Media) {
        mediaDao.updateMedia(media)
    }

    suspend fun delete(media: Media) {
        mediaDao.deleteMedia(media)
    }

}