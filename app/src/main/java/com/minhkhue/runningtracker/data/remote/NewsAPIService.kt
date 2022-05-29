package com.minhkhue.runningtracker.data.remote

import com.minhkhue.runningtracker.model.remote.NewsResponse
import com.minhkhue.runningtracker.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode:String = "us",
        @Query("category")
        category: String = "health",
        @Query("apiKey")
        apiKey: String = Constant.NEWS_API_KEY
    ) : Response<NewsResponse>
}