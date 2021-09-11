package com.shakib_mansoori.newsapp.repository

import com.shakib_mansoori.newsapp.Model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    // https://newsapi.org/v2/everything?q=apple&from=2021-09-08&to=2021-09-08&sortBy=popularity&apiKey=6837b1fe03d242d4ba22ff7a91502252
    // category=health&language=en

    @GET("v2/top-headlines")
    suspend fun fetchNews(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
    ): Response<News>


    @GET("v2/top-headlines?category=health&language=en")
    suspend fun fetchHealthNews(
        @Query("apiKey") apiKey: String,
    ): Response<News>

    @GET("v2/top-headlines?category=politics&language=en")
    suspend fun fetchPoliticsNews(
        @Query("apiKey") apiKey: String,
    ): Response<News>

    @GET(" v2/top-headlines?category=science&language=en")
    suspend fun fetchScienceNews(
        @Query("apiKey") apiKey: String,
    ): Response<News>


    @GET(" v2/top-headlines?category=business&language=en")
    suspend fun fetchArtNews(
        @Query("apiKey") apiKey: String,
    ): Response<News>

    @GET(" v2/top-headlines?category=technology&language=en")
    suspend fun fetchFoodNews(
        @Query("apiKey") apiKey: String,
    ): Response<News>

}