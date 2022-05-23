package com.minhkhue.runningtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhkhue.runningtracker.model.remote.NewsResponse
import com.minhkhue.runningtracker.repository.NewsRepository
import com.minhkhue.runningtracker.utils.NetworkHelper
import com.minhkhue.runningtracker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _news = MutableLiveData<Resource<NewsResponse>>()
    val news: LiveData<Resource<NewsResponse>> get() = _news
    init {
        getNews()
    }
    private fun getNews() = viewModelScope.launch {
        _news.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            launch(Dispatchers.IO) {
                newsRepository.getNews().let {
                    if (it.isSuccessful) {
                        _news.postValue(Resource.success(it.body()))
                    } else {
                        _news.postValue(Resource.error(it.message().toString(), null))
                    }
                }
            }
        } else {
            _news.postValue(Resource.error("No Internet Connection", null))
        }

    }
}