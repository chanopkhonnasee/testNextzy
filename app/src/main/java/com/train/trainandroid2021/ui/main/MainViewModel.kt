package com.train.trainandroid2021.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.train.trainandroid2021.repository.Repository
import com.train.trainandroid2021.repository.model.Article
import com.train.trainandroid2021.repository.model.NewsResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val repository: Repository) : ViewModel() {

    /** live data **/
    val newsData = MutableLiveData<NewsResponse>()
    var artical = MutableLiveData<Article>()

    fun getNews(query: String) {
        repository.getNews(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newsData.value = it
            }, {
                Log.i("MAIN", it.message.toString())
            })
    }

    fun getDetail(data: Article) {
        artical.value = data
    }
}