package com.example.test6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() { // AppCompatActivity 부모 클래스로 부터 상속 받음.
    // 코드 작업 공간, 뷰 작업도 가능하지만, 가급적 뷰는 XML에서 작업하기.
    // 이유? -> 여기에서는 데이터 관련 작업 및 이벤트 핸들러 등 작업하기위해서, 분리.
    // 만약, 한 파일에 뷰까지 더해서, 작업을 하게되면, 가독성이 않좋음, 유지보수도 어려움. 분리.
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate 액티비티의 생명주기에서 더 설명. 13장
        super.onCreate(savedInstanceState) // saveInstanceState -> 저장소 개념16장에서 더 설명. 객체에 저장.
        // res -> layout -> XML파일을 불러와서, 실제로 화면 출력.
        // 리소스 R.java 파일이라는 곳에 상수 값으로 저장되는데, 여기서 불러옵니다.
        // 그래서, 코드에서 리소스의 값을 불러올 때 항상. R.layout 등올 시작을해서 불러 옵니다.
        // 화면을 출력 하겠다. 레이아웃 7장 더 설명
        // (뷰 그룹 : 뷰(텍스트, 이미지, 인풋, 체크박스, 라디오...)를 모아주는 역할
        setContentView(R.layout.activity_main)
    }
}