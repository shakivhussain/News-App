package com.shakib_mansoori.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shakib_mansoori.newsapp.Model.Articles
import com.shakib_mansoori.newsapp.Model.News
import com.shakib_mansoori.newsapp.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repo {


    private var healthNews: MutableLiveData<List<Articles>> = MutableLiveData()
    private var politicsNews: MutableLiveData<List<Articles>> = MutableLiveData()
    private var sciencNews: MutableLiveData<List<Articles>> = MutableLiveData()
    private var artNews: MutableLiveData<List<Articles>> = MutableLiveData()
    private var foodNews: MutableLiveData<List<Articles>> = MutableLiveData()
    private var news: MutableLiveData<News> = MutableLiveData()

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)


    suspend fun fetchAllNews() {

        val newsResponse = api.fetchHealthNews(Constant.API_KEY)
        if (newsResponse.isSuccessful) {
            val list = newsResponse.body()
            news.postValue(list)
        }

        val healthNewsResponse = api.fetchHealthNews(Constant.API_KEY)
        if (healthNewsResponse.isSuccessful) {
            val list = healthNewsResponse.body()?.articles
            healthNews.postValue(list)
        }


        val politicsNewsResponse = api.fetchPoliticsNews(Constant.API_KEY)
        if (politicsNewsResponse.isSuccessful) {
            val list = politicsNewsResponse.body()?.articles
            politicsNews.postValue(list)
        }


        val scienceNewsResponse = api.fetchScienceNews(Constant.API_KEY)
        if (scienceNewsResponse.isSuccessful) {
            val list = scienceNewsResponse.body()?.articles
            sciencNews.postValue(list)
        }

        val artNewsResponse = api.fetchArtNews(Constant.API_KEY)
        if (artNewsResponse.isSuccessful) {
            val list = artNewsResponse.body()?.articles
            artNews.postValue(list)
        }

        val foodNewsResponse = api.fetchFoodNews(Constant.API_KEY)
        if (foodNewsResponse.isSuccessful) {
            val list = foodNewsResponse.body()?.articles
            foodNews.postValue(list)
        }

    }


    fun getHealthNews(): LiveData<List<Articles>> {
        return healthNews
    }

    fun getPoliticsNews(): LiveData<List<Articles>> {
        return politicsNews
    }

    fun getScienceNews(): LiveData<List<Articles>> {
        return sciencNews
    }

    fun getArtNews(): LiveData<List<Articles>> {
        return artNews
    }

    fun getFoodNews(): LiveData<List<Articles>> {
        return foodNews
    }

    fun newsContainer(): LiveData<News> {
        return news
    }

}