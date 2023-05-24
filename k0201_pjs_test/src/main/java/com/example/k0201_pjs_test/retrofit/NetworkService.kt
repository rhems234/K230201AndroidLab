package com.example.k0201_pjs_test.retrofit

import com.example.k0201_pjs_test.model.PageListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {


    //부산맛집정보성비스
    // itemModel, PageListModel 참고
//    http://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=인증키&numOfRows=10&pageNo=1
    @GET("AttractionService/getAttractionKr")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("resultType") resultType : String
    ): retrofit2.Call<PageListModel>

}