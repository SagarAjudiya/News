package com.simform.news.ui.headlines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simform.news.data.model.Article
import com.simform.news.repository.HeadlineRepository

class HeadlinesVM : ViewModel() {

    private val homeRepository = HeadlineRepository()
    private var liveDataNews = MutableLiveData<ArrayList<Article>>()
    var category: String = ""

    val news: LiveData<ArrayList<Article>>
        get() = liveDataNews

    fun getNews() {
        homeRepository.getNews(category) {
            liveDataNews.postValue(ArrayList(it.articles))
        }
    }

}