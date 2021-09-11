package com.shakib_mansoori.newsapp.Model

data class News(

    var status: String,
    var totalResults: String,
    var articles: List<Articles>,
) {


}