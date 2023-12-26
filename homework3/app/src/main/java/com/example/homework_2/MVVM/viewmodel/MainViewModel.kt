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

class MainViewModel(private val state: SavedStateHandle) : ViewModel()
{
    private val itemsKey = "ITEMS"
    var items: MutableLiveData<ArrayList<Response.Item>> = state.getLiveData(itemsKey, arrayListOf())

    private val statusKey = "STATUS"
    var status: MutableLiveData<StatusLoad> = state.getLiveData(statusKey, StatusLoad.SUCCESS)

    private val connector = Connector
    private val provider = connector.provider()

    fun addItems(limit: Int = 100, question: String)
    {
        viewModelScope.launch {
            val offset = items.value?.size ?: 0

            status.value = StatusLoad.LOADING

            delay(2000L)

            val data = getItems(offset, limit, question)
            if (data.isNotEmpty()) {
                items.value?.addAll(data)
                status.value = StatusLoad.SUCCESS
            }
        }
    }

    private suspend fun getItems(offset: Int = 0, limit : Int = 100, question: String) : ArrayList<Response.Item>
    {
        try
        {
            val request = withContext(Dispatchers.IO) {
                provider.getItems(offset, limit, question)
            }
            return request.data
        }
        catch (error: Throwable)
        {
            status.value = StatusLoad.ERROR
        }
        return arrayListOf()
    }
}