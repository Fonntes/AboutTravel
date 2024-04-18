package com.example.abouttravel.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abouttravel.data.db.AboutTravelDataBase
import com.example.abouttravel.data.entities.Media
import com.example.abouttravel.data.repository.MediaRepository
import com.example.abouttravel.data.service.MediaService
import kotlinx.coroutines.launch

class MediaViewModel(application: Application) : AndroidViewModel(application) {

    private val mediaService: MediaService
    val allMedia: LiveData<List<Media>>

    init {
        val mediaDao = AboutTravelDataBase.getDatabase(application).mediaDao()
        val mediaRepository = MediaRepository(mediaDao)
        mediaService = MediaService(mediaRepository)
        allMedia = mediaService.allMedia
    }

    fun insert(media: Media) = viewModelScope.launch {
        mediaService.insert(media)
    }

    fun update(media: Media) = viewModelScope.launch {
        mediaService.update(media)
    }

    fun delete(media: Media) = viewModelScope.launch {
        mediaService.delete(media)
    }
}