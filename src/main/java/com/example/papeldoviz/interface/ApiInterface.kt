package com.example.papeldoviz.`interface`

import com.example.papeldoviz.servis.MyDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("currencies/ticker?key=f878bb1bd8cf652e0f4b2f50a69872b7b352dd18&interval=1d,30d&convert=USD&per-page=100&page=1")
    fun getData(): Call<List<MyDataItem>>

    @GET("currencies/ticker?key=f878bb1bd8cf652e0f4b2f50a69872b7b352dd18&&interval=1d,30d&convert=USD&per-page=100&page=1")
    fun getDataGraph(@Query("ids") ids: String
    ): Call<MyDataItem>
}