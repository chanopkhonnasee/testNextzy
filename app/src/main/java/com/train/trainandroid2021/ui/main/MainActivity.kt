package com.train.trainandroid2021.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.train.trainandroid2021.R
import com.train.trainandroid2021.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            clickListener = this@MainActivity.articleListenner
        }
    }
    lateinit var articleListenner: MainActivityListenner

    private var articleAdapter: ArticleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEvent()
        initViewModel()
        initViewData()

//        setContentView(R.layout.activity_main)
//        initFragment()
    }

//    private fun initFragment() {
//        supportFragmentManager.transaction {
//            replace(R.id.main_container, MainFragment.newInstance(), "Main")
//        }
//    }

    private fun initEvent() {
        articleListenner = MainActivityListenner {
            viewModel.getNews(etSearch.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel.newsData.observe(this, Observer {
            articleAdapter?.articles = it.articles
        })
    }

    @SuppressLint("ShowToast")
    private fun initViewData() {
        articleAdapter = ArticleAdapter(ArticleAdapter.ArticleListenner { article ->
            val json = Gson().toJson(article)
            val bundle = Bundle()
            bundle.putString("article",json)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })
        binding.adapter = articleAdapter
        viewModel.getNews("Apple")
    }

    inner class MainActivityListenner(val clickListener: () -> Unit) {
        fun onClickSearch() = clickListener()
    }

}