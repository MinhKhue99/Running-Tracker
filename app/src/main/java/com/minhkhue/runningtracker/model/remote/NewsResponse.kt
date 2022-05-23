package com.minhkhue.runningtracker.model.remote

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)