package com.shakib_mansoori.newsapp.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakib_mansoori.newsapp.Model.Articles
import com.shakib_mansoori.newsapp.Model.News
import com.shakib_mansoori.newsapp.repository.Repo
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val articlesContainer = MutableLiveData<List<Articles>>()

    fun fetchNewsDataFromApi() {
        viewModelScope.launch {

            Repo.fetchAllNews()

        }
    }


    fun getHealthNews(): LiveData<List<Articles>> {
        return Repo.getHealthNews()
    }


    fun getPoliticsNews(): LiveData<List<Articles>> {
        return Repo.getPoliticsNews()
    }

    fun getScienceNews(): LiveData<List<Articles>> {
        return Repo.getScienceNews()
    }

    fun getArtNews(): LiveData<List<Articles>> {
        return Repo.getArtNews()
    }

    fun getFoodNews(): LiveData<List<Articles>> {
        return Repo.getFoodNews()
    }

    fun newsContainer(): LiveData<News> {
        return Repo.newsContainer()
    }

}
