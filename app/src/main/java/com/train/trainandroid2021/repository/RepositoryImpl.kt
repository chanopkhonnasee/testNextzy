package com.train.trainandroid2021.repository

import com.train.trainandroid2021.repository.model.NewsResponse
import com.train.trainandroid2021.repository.service.ApiInterface
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(private val dataSource: ApiInterface) : Repository {
    override fun getNews(query: String): Observable<NewsResponse> {
        return dataSource.getNews(query)
    }
}