package com.train.trainandroid2021.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.train.trainandroid2021.R
import com.train.trainandroid2021.databinding.ActivityDetailBinding
import com.train.trainandroid2021.repository.model.Article
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {


    private val viewModel by viewModel<MainViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail).apply {
            viewModel = this@DetailActivity.viewModel
            clickListener = this@DetailActivity.detailListenner
        }
    }
    lateinit var detailListenner: DetailActivityListenner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initViewData()
        initEvent()
    }

    private fun initEvent() {
        detailListenner = DetailActivityListenner ({
            onBackPressed()
        },{
            val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(it))
            startActivity(browserIntent)
        })
    }

    private fun initViewModel() {
        viewModel.artical.observe(this, Observer {
            binding.data = it
        })
    }

    private fun initViewData() {
        val bundle = intent.extras
        bundle?.let{
            val json = bundle.getString("article")
            val data = Gson().fromJson(json, Article::class.java)
            viewModel.getDetail(data)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    inner class DetailActivityListenner(val clickListener: () -> Unit,val webListener: (url: String) -> Unit) {
        fun onClickBack() = clickListener()
        fun onClickWeb(url: String) = webListener(url)
    }
}