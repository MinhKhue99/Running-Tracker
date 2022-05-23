package com.minhkhue.runningtracker.repository

import com.minhkhue.runningtracker.model.remote.NewsResponse
import retrofit2.Response

interface INewsRepository {
    suspend fun getNews():Response<NewsResponse>
}