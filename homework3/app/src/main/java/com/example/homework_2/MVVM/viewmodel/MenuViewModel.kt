package com.example.homework_2.MVVM.viewmodel

import androidx.lifecycle.*
import com.example.homework_2.utils.Connector
import com.example.homework_2.StatusLoad
import com.example.homework_2.MVVM.model.Response
import com.example.homework_2.utils.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel() : ViewModel()
{
    private var offset = 0;
    var item = MutableLiveData<Response.Item>()

    var status = MutableLiveData<StatusLoad>()

    private val connector = Connector
    private val provider = connector.provider()

    fun getNewItem(question: String)
    {
        viewModelScope.launch {
            status.value = StatusLoad.LOADING

            delay(2000L)

            getItem(offset, question)?.let {
                item.value = it
                status.value = StatusLoad.SUCCESS
                offset += 1
            } ?: run {
                status.value = StatusLoad.ERROR
            }
        }
    }

    private suspend fun getItem(offset: Int = 0, question: String) : Response.Item?
    {
        try
        {
            val request = withContext(Dispatchers.IO) {
                provider.getItems(offset, 1, question)
            }
            return request.data[0]
        }
        catch (error: Throwable)
        {
            status.value = StatusLoad.ERROR
        }
        return null
    }
}