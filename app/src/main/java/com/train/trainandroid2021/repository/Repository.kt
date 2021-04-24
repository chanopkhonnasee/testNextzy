package com.train.trainandroid2021.repository

import com.train.trainandroid2021.repository.model.NewsResponse
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getNews(query: String): Observable<NewsResponse>
}