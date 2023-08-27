package com.simform.news.ui.headlines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simform.news.data.model.Article

class DataViewModel : ViewModel() {
    val dataList: MutableLiveData<Article> = MutableLiveData()
}