package com.minhkhue.runningtracker.di

import com.google.gson.Gson
import com.minhkhue.runningtracker.data.remote.APIHelper
import com.minhkhue.runningtracker.data.remote.APIHelperImpl
import com.minhkhue.runningtracker.data.remote.APIService
import com.minhkhue.runningtracker.data.remote.NewsAPIService
import com.minhkhue.runningtracker.utils.Constant.BASE_URL
import com.minhkhue.runningtracker.utils.Constant.NEWS_API_KEY
import com.minhkhue.runningtracker.utils.Constant.NEWS_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun providesInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    @Named("MealDB")
    fun provideRetrofit(
        httpClient: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @Named("NewsAPI")
    fun provideNewsRetrofit(
        httpClient: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApiService(@Named("MealDB") retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Provides
    fun provideNewsApiService(@Named("NewsAPI") retrofit: Retrofit): NewsAPIService =
        retrofit.create(NewsAPIService::class.java)

    @Provides
    fun provideAPIHelper(apiHelper: APIHelperImpl): APIHelper = apiHelper
}