package com.example.test18.retrofit

import com.example.test18.model.UserListModel
import com.example.test18.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


//순서3
//서버인테페이스 정의.
//메인 부분.
//네트워크 통신이 필요한 순간에 호출할 함수를 작성한다.
interface INetworkService {
    @GET("api/users")
    // baseurl : https://reqres.in/
    // https://reqres.in/api/users?page=2
    // 예를 들어서 doGetUserList(2)
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //@GET("users/list?sort=desc")

    @GET("api/users/2")
    fun test1(): Call<UserModel>
}