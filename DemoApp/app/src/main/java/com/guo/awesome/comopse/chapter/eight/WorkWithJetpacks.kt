package com.guo.awesome.comopse.chapter.eight

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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


fun testFlow(): Flow<Int> = flow {
    for (i in 0..10) {
        kotlinx.coroutines.delay(1000)
        emit(i)
    }
}

@Composable
fun TestFlow() {
    val flowValue = testFlow().collectAsState(initial = 0)
    Text(
        text = flowValue.value.toString(),
        modifier = Modifier.fillMaxWidth(),
        fontSize = 32.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TestRxJava() {
    //Observable<Int>.subscribeAsState(initial = 0)
}


@Composable
fun TestPaging(flow: Flow<PagingData<String>>) {
    val lazyPagingItems = flow.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyPagingItems) {
            Text("Item is $it")
        }

        val state = lazyPagingItems.loadState
        when {
            state.prepend is LoadState.Loading -> {

            }
            state.prepend is LoadState.Error -> {

            }
            state.prepend is LoadState.NotLoading -> {

            }
            state.append is LoadState.Loading -> {

            }
        }
    }
}