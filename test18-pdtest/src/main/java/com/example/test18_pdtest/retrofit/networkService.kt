package com.example.test18_pdtest.retrofit

import com.example.test18_pdtest.model.UserListModel
import com.example.test18_pdtest.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


//순서3
//서버인테페이스 정의.
//메인 부분.
//네트워크 통신이 필요한 순간에 호출할 함수를 작성한다.
interface networkService {
    //@GET("api/users")
    @GET("getFoodKr")
    // baseurl : https://reqres.in/
    // https://reqres.in/api/users?page=2
    // 예를 들어서 doGetUserList(2)

    // 현재 접근 하기 위한 공공데이터 서버의 api 주소의 예제
    // http://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=인증키&numOfRows=10&pageNo=1
    fun doGetUserList
                (
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
    ): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //@GET("users/list?sort=desc")

    @GET("api/users/2")
    fun test1(): Call<UserModel>
}