package com.example.homework_2.data.api

import com.example.homework_2.MVVM.model.Response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemRequest
{
    @GET("/v1/gifs/search") // https://api.com/v1/gifs/search/?api_key=1263721637123&q="CAT"
    suspend fun getItems(
        @Query("api_key") api_key: String,
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
    ) : Response

    companion object
    {
        fun createRequest(baseUrl: String): ItemRequest
        {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(baseUrl)
            }.build()

            return retrofit.create(ItemRequest::class.java)
        }
    }
}