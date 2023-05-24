package com.example.k0201_pjs_test

import android.app.Application
import com.example.k0201_pjs_test.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    //add....................................
    var networkService: NetworkService


    // http://apis.data.go.kr/6260000/AttractionService/getAttractionKr?serviceKey=인증키&numOfRows=10&pageNo=1
    val retrofit: Retrofit
        get() = Retrofit.Builder()
                //도보여행, 부산맛집정보서비스
            .baseUrl("https://apis.data.go.kr/6260000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkService::class.java)
    }
}