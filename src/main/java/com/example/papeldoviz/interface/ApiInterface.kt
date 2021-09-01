package com.example.papeldoviz.`interface`

import com.example.papeldoviz.servis.MyDataItem
import com.example.papeldoviz.servis.MyListChartList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("currencies/ticker?key=f878bb1bd8cf652e0f4b2f50a69872b7b352dd18&interval=1d,30d&convert=USD&per-page=100&page=1")
    fun getData(): Call<List<MyDataItem>>

    @GET("currencies/ticker?key=f878bb1bd8cf652e0f4b2f50a69872b7b352dd18&&interval=1d,30d&convert=USD&per-page=100&page=1")
    fun getDataGraph(@Query("ids") ids: String
    ): Call<MyDataItem>

    @GET("https://api.nomics.com/v1/currencies/sparkline?key=7ce49c49d0a6b5e38b62535e2924fa187452dc36&&&")
    fun getLineChart(
        @Query("ids") ids: String,
        @Query("start") start: String,
        ): Call<List<MyListChartList>>
}