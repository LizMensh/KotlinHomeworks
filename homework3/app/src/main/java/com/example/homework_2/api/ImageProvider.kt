package com.example.homework_2.data.api

import com.example.homework_2.ResponseParameters
import com.example.homework_2.MVVM.model.Response

class ImageProvider(private val getRequest : ItemRequest)
{

    suspend fun getItems(
        offset : Int, limit : Int,
        question: String = ResponseParameters.Q.value): Response
    {
        return getRequest.getItems(
            ResponseParameters.API_KEY.value,
            question,
            limit,
            offset,
            ResponseParameters.RATING.value,
            ResponseParameters.LANG.value)
    }
}