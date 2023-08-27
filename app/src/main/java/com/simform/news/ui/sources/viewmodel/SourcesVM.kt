package com.simform.news.ui.sources.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.news.data.model.Source
import com.simform.news.repository.SourcesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SourcesVM : ViewModel() {

    private val sourcesRepository = SourcesRepository()
    private var liveDataSources = MutableLiveData<ArrayList<Source>>()

    val resources: LiveData<ArrayList<Source>>
        get() = liveDataSources

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun getSources() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            liveDataSources.postValue(sourcesRepository.getSources()?.sources)
        }
    }

}