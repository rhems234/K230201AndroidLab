package com.example.test18_newsapi.retrofit

import com.example.test18_newsapi.model.PageListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("/v2/everything")
    //https://newsapi.org/v2/everything?q=Apple&from=2023-05-23&sortBy=popularity&apiKey=API_KEY
    fun getList(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("from") from: String?,
        @Query("sortBy") sortBy: String?
//        @Query("page") page: Long,
//        @Query("pageSize") pageSize: Int
    ): Call<PageListModel>
}