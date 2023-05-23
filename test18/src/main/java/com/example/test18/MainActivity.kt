package com.example.test18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test18.databinding.ActivityMainBinding
import com.example.test18.model.UserListModel
import com.example.test18.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // test, MyApplication에서 만든 형으로 다운캐스팅해서, 해당 객체 생성.
        val networkService = (applicationContext as MyApplication).networkService

        // INeworkService에 정의된 추상 함수이고, MyApplication 클래스에 붙였다.
        // 예 doGetUserList(2)
        // 여기에 함수의 형식이 계속 변경이 됩니다.
        val userListCall = networkService.doGetUserList("2")

        // 버튼1을 누르면 해당 test1()의 결과값 받아오기.
        binding.btn1test1.setOnClickListener {
            // 해당 애너테이션 정의 부분 사용해보기 예제2) @GET("users/list?sort=desc")
            val test1 = networkService.test1()

            test1.enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    val userModelSample = response.body()
                    Log.d("lsy", "test1()호출예제2이고 값 조회 : ${userModelSample}")
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        // 실제 통신을 해서 사용하기. 서버와 통신해서, 데이터를 받는다.
        userListCall.enqueue(
            // Callback : import 함.
            object : Callback<UserListModel> {
                // Callback 인터페이스를 구현 특정 표현식을 쓸 때,
                // 의무적으로 재정의를 해야하는 함수2개.
                // onResponse : 서버로 부터 응답 성공 했을 때, 수행하는 함수.
                // onFailure : 실패 했을 때, 수행하는 삼수.
                override fun onResponse(
                    call: Call<UserListModel>,
                    response: Response<UserListModel>
                ) {
                    // 정삭적으로 서버로부터 데이터를 받아왔다.
                    // 실제로 데이터를 받고나서, response.body 곳에 data가 있음.
                    val userList = response.body()

                    // 받아온 데이터를 콘솔에 출력.
                    // 실제 작업은 뷰에 바인딩.
                    // 리사이클러 뷰를 작업.
                    Log.d("lsy", "userList(response.body)의 값 : ${userList}")
                }

                override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

    }
}