package com.guo.awesome.comopse.chapter.eight

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class MyViewModel(defaultVal: Int) : ViewModel() {
    private val _count = MutableLiveData(defaultVal)
    val count: LiveData<Int>
        get() = _count

    fun onValueChanged(value: Int) {
        _count.postValue(value)
    }
}

class MyViewModelFactory(private val value: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(value) as T
    }
}

@Composable
fun MyScreen() {
    val key = "key_count_number"
    val sp = LocalContext.current.getSharedPreferences("sp_file", Context.MODE_PRIVATE)
    val preVal = sp.getInt(key, 0)
    val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(preVal))
    val count by viewModel.count.observeAsState(preVal)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count.toString(), modifier = Modifier.padding(8.dp))
        Button(onClick = {
            val newCount = count + 1
            viewModel.onValueChanged(newCount)
            sp.edit { putInt(key, newCount) }
        }) {
            Text(text = "Increment")
        }
    }
}