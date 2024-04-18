package com.example.abouttravel.data.service

import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.repository.MediaRepository

class MediaService( private val mediaRepository: MediaRepository) {

    val allMedia = mediaRepository.allMedia

    suspend fun insert(media: Media) {
        mediaRepository.insert(media)
    }

    suspend fun update(media: Media) {
        mediaRepository.update(media)
    }

    suspend fun delete(media: Media) {
        mediaRepository.delete(media)
    }

}