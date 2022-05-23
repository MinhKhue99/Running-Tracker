package com.minhkhue.runningtracker.repository

import com.minhkhue.runningtracker.data.remote.APIHelper
import com.minhkhue.runningtracker.model.remote.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiHelper: APIHelper) : INewsRepository {
    override suspend fun getNews(): Response<NewsResponse> =
        apiHelper.getNews()
}