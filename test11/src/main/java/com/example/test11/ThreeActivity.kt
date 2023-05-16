package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class ThreeActivity : AppCompatActivity() {

    //(menu)XML에서 마는 메뉴를 해당 엑티비티 화면에 적용하는 코드임
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 한줄 표현 -> menuInflater.inflate(R.menu.menu_three,menu)

        //서치 뷰 이벤트 처리 관련 코드 ->
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_three,menu)
        val menuItem = menu?.findItem(R.id.searchMenu)
        val searchView = menuItem?.actionView as SearchView

        //액션 바 -> 서치뷰 -> 이벤트핸들러 재정의
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            //검색어 변경시 수행하는 함수
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("홍길동" , "변경시 내용 : ${newText}")
                return true
            }
            //검색 버튼 클릭시 이벤트
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                Toast.makeText(this@ThreeActivity, "클릭시 이벤트 발생", Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
    }
}