package com.example.test18

import android.app.Application
import retrofit.INetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    // MyApplication : Application() 상속을 받아야함.
    // INetworkService 인터페이스 타입의 변수를 선언
    // Retrofit 타입의 객체를 생성
    // retrofit.create(INetworkService::class.java)
    // 위에서 정의한 인터페이스를 구현한 객체를 반환한 값
    // networkService에 할당함.

    // 주의사항, 사용여부 -> 매니페스트 파일의 <application 태그 내부에
    // name으로 설정해서, 해당 앱을 실행할 대, 메모리상에 등록해서 사용한다.

    // 선언만
    var networkService: INetworkService

//    순서4
//    Retrofit 객체 생성
//    예시)
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init { // 클래스를 생성 또는 사용하면, 할당작업.
        networkService = retrofit.create(INetworkService::class.java)
    }
}