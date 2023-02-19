package com.compose.awsome.techchat.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.compose.awsome.techchat.model.article.ArticleDetailResponse
import com.compose.awsome.techchat.model.article.ArticleResponse
import com.compose.awsome.techchat.net.ApiService
import com.compose.awsome.techchat.web3.Awesometech
import com.compose.awsome.techchat.web3.Web3Constants
import com.guo.vote.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

class ArticleViewModel : BaseViewModel() {

    var currentTag by mutableStateOf(2)

    val articlesLiveData = MutableLiveData<ArticleResponse>()

    private lateinit var web3j: Web3j
    private val credentials = Credentials.create(Web3Constants.PRIVATE_KEY)

    val submitState = MutableLiveData(1)
    val readState = MutableLiveData(1)
    var currentLikeCount by mutableStateOf(0L)

    fun fetchArticleList() {
        launch {
            val articlesModel = ApiService.getArticles()
            articlesLiveData.value = articlesModel
        }
    }

    val articleDetailLiveData = MutableLiveData<ArticleDetailResponse>()
    fun fetchArticleDetail(articleID: Long) {
        launch {
            val articleModel = ApiService.getArticleDetail()
            articleDetailLiveData.value = articleModel
        }
    }

    fun initWeb3() {
        launch {
            try {
                web3j = Web3j.build(HttpService(Web3Constants.ROPSTEN_URL))
            } catch (wtf: java.lang.Exception) {
                val exception: String = wtf.toString()
                Log.d("wat", "Error initializing web3j/infura. Error: $exception")
            }
        }
    }

    fun doLike() {
        if (submitState.value == 1) {
            submitState.value = 0
            viewModelScope.launch(Dispatchers.IO) {
                val awt: Awesometech = Awesometech.load(
                    Web3Constants.AWESOME_TECH_CONTRACT_ADDRESS,
                    web3j,
                    credentials,
                    BigInteger.valueOf(10 + 1000000000L),
                    BigInteger.valueOf(31000)
                )
                val transactionReceipt = awt.doLike().send()
                submitState.postValue(1)
                currentLikeCount += 1
            }
        }
    }

    fun readLikeCount() {
        readState.value = 0;
        viewModelScope.launch(Dispatchers.IO) {
            val awt: Awesometech = Awesometech.load(
                Web3Constants.AWESOME_TECH_CONTRACT_ADDRESS,
                web3j,
                credentials,
                BigInteger.valueOf(10 + 1000000000L),
                BigInteger.valueOf(31000)
            )

            try {
                val likeCount = awt.likeCount.send()
                currentLikeCount = likeCount.toLong()
                readState.postValue(1)
            } catch (e: Throwable) {
                Log.w("guoxj", "error: $e")
            }

        }
    }
}
