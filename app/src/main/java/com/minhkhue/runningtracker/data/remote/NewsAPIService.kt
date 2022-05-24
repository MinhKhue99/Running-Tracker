package com.minhkhue.runningtracker.data.remote

import com.minhkhue.runningtracker.model.remote.NewsResponse
import com.minhkhue.runningtracker.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("/v2/everything")
    suspend fun getNews(
//        @Query("country")
//        countryCode:String = "us",
        @Query("q")
        category: String = "fitness",
        @Query("sortBy")
        sortBy: String = "publishedAt",
        @Query("apiKey")
        apiKey: String = Constant.NEWS_API_KEY
    ) : Response<NewsResponse>
}