package com.example.homework_2.MVVM.viewmodel

import androidx.lifecycle.*
import com.example.homework_2.utils.Connector
import com.example.homework_2.StatusLoad
import com.example.homework_2.MVVM.model.Response
import com.example.homework_2.utils.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val state: SavedStateHandle) : ViewModel()
{
    private val recievedItemsKey = "RECIEVED_ITEMS"
    var receivedItems: MutableLiveData<ArrayList<Response.Item>> = state.getLiveData(recievedItemsKey, arrayListOf())

    private val itemsKey = "ITEMS"
    var items: MutableLiveData<ArrayList<Response.Item>> = state.getLiveData(itemsKey, arrayListOf())

    private val statusKey = "STATUS"
    var status: MutableLiveData<StatusLoad> = state.getLiveData(statusKey, StatusLoad.SUCCESS)

    private val connector = Connector
    private val provider = connector.provider()

    fun addItems(limit: Int = 100)
    {
        viewModelScope.launch {
            val offset = items.value?.size ?: 0

            receivedItems.value = getItems(offset, limit)
            items.value?.addAll(receivedItems.value ?: arrayListOf())
        }
    }

    private suspend fun getItems(offset: Int = 0, limit : Int = 100) : ArrayList<Response.Item>?
    {
        status.value = StatusLoad.LOADING
        try
        {
            val request = withContext(Dispatchers.IO) {
                provider.getItems(offset, limit)
            }
            status.value = StatusLoad.SUCCESS
            return request.data
        }
        catch (error: Throwable)
        {
            status.value = StatusLoad.ERROR
        }
        return null
    }
}