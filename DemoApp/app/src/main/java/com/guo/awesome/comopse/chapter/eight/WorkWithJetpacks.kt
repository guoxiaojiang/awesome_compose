package com.guo.awesome.comopse.chapter.eight

import android.content.Context
import android.graphics.drawable.AnimatedImageDrawable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.guo.awesome.comopse.chapter.R
import com.guo.awesome.comopse.chapter.five.padding
import com.guo.awesome.comopse.chapter.theme.helloWorld
import com.guo.awesome.comopse.chapter.theme.textSample

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


@HiltViewModel
class MyViewModel1 @Inject constructor(defaultVal: Int) : ViewModel() {
    private val _count = MutableLiveData(defaultVal)
    val count: LiveData<Int>
        get() = _count

    fun onValueChanged(value: Int) {
        _count.postValue(value)
    }
}

@Composable
fun MyScreen1() {
    val viewModel: MyViewModel1 = viewModel(factory = MyViewModelFactory(0))
}


@Composable
fun TestTextResource() {
    Text(text = "这就是需要显示的字符串内容")
    var name = "Alex"
    var age = 20
    Text(text = "他的名字是$name, 年龄:$age")
    Text(text = helloWorld)
    Text(text = textSample)
    Text(text = stringResource(R.string.hello_world))
    Text(text = stringResource(id = R.string.congratulate, "the Autumn Day", 2022))
}

@Composable
fun TestDimenResource() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "...")
    }

    val smallPadding = dimensionResource(R.dimen.padding_small)
    Column(modifier = Modifier.padding(smallPadding)) {
        Text(text = "...")
    }
}

@Composable
fun TestColorResource() {
    Text(text = helloWorld, color = colorResource(id = R.color.purple_200))
}

@ExperimentalAnimationGraphicsApi
@Composable
fun TestImageResource() {
    Image(painter = painterResource(id = R.drawable.scenary), contentDescription = "")
    Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")

    //矢量动画
    val image = AnimatedImageVector.animatedVectorResource(id = R.drawable.ic_launcher_background)
    val atEnd by remember { mutableStateOf(false) }
    Image(
        painter = rememberAnimatedVectorPainter(image, atEnd),
        contentDescription = ""
    )
    
    Icon(Icons.Rounded.Star, contentDescription = "")
}

