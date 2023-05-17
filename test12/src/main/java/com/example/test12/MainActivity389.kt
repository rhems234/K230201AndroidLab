package com.example.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test12.databinding.ActivityMain389Binding

class MainActivity389 : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 부분 공통, 뷰 객체 한번에 처리 할려고
        val binding = ActivityMain389Binding.inflate(layoutInflater)

        setContentView(binding.root)

        // 개발자가 만든 툴바를 직접 설정. 넣기.
        setSupportActionBar(binding.toolbar)

        // 해당 툴바에 아이콘 추가해서 , 클릭시, 드로워 메뉴가 나오는 작업부분
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)

        // 툴바에 아이콘 보이게 설정
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 툴바에 아이콘 <-> 드로워 메뉴 동기화 부분.
        toggle.syncState()

        // 드로워 메뉴에서, 빨간 부분이 헤더 부분, 아래에 메뉴 부분 : 네비게이션 메뉴
        // 해당 네비게이션 뷰 클릭시 이벤트 리스너 부분, 항상, 뷰의 리스너 부분은 각각 다릅니다.
        // 클릭시 : 로그캣 창에서 해당 아이템의 제목이 나옴.
        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("kkang","navigation item click... ${it.title}")
            true
        }
    }

    // 툴바에 아이콘 클릭시, 드로우 메뉴가, 왼쪽 -> 오른쪽 나오게 연결하는 부분.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}