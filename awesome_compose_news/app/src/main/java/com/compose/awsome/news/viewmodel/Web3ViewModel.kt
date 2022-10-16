package com.compose.awsome.news.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.compose.awsome.news.data.Web3Data
import com.compose.awsome.news.web3.Web3Constants
import com.guo.vote.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

class Web3ViewModel : BaseViewModel() {

    val newsLiveData = MutableLiveData<Web3Data>()
    val submitState = MutableLiveData(1)
    val readState = MutableLiveData(1)
    val stateStr = MutableLiveData("初始化中..")
    private lateinit var web3j: Web3j
    private val credentials = Credentials.create(Web3Constants.PRIVATE_KEY)
    fun fetchNewsList() {
        launch {
//            val newsModel = ApiService.getNews()
//            newsLiveData.value = newsModel
        }
    }

    fun initWeb3() {
        launch {
            var result = "Success initializing web3j/infura"
            try {
                web3j = Web3j.build(HttpService(Web3Constants.ROPSTEN_URL))
            } catch (wtf: java.lang.Exception) {
                val exception: String = wtf.toString()
                Log.d("wat", "Error initializing web3j/infura. Error: $exception")
            }
            stateStr.value = result
        }
    }

    fun submit(name: String) {
        submitState.value = 0;
        viewModelScope.launch(Dispatchers.IO) {
            val test: Test = Test.load(
                Web3Constants.NAME_CONTRACT_ADDRESS,
                web3j,
                credentials,
                BigInteger.valueOf(10 + 1000000000L),
                BigInteger.valueOf(31000)
            )
            val transactionReceipt = test.setName(name).send()
            try {
                stateStr.postValue("Successful transaction. Gas used: " + transactionReceipt.gasUsed)
            } catch (e: Throwable) {
                Log.w("guoxj", "error: $e")
            }
            submitState.postValue(1)
        }
    }

    fun read() {
        readState.value = 0;
        viewModelScope.launch(Dispatchers.IO) {
            val test: Test = Test.load(
                Web3Constants.NAME_CONTRACT_ADDRESS,
                web3j,
                credentials,
                BigInteger.valueOf(10 + 1000000000L),
                BigInteger.valueOf(31000)
            )
            val readName = test.name.send()
            try {
                stateStr.postValue("从智能链上读取到了值：$readName")
            } catch (e: Throwable) {
                Log.w("guoxj", "error: $e")
            }
            readState.postValue(1)
        }
    }

}