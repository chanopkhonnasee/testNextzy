package com.train.trainandroid2021.repository.service

import com.train.trainandroid2021.BuildConfig
import com.train.trainandroid2021.repository.model.NewsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v2/everything?apiKey=${BuildConfig.API_KEY}")
    fun getNews(@Query("q") q: String): Observable<NewsResponse>
}