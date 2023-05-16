package com.example.test11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.test11.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // 재정의 샘플
    /* override fun onSupportNavigateUp(): Boolean {
         Toast.makeText(this@MainActivity,"메인 업버튼 동작확인",Toast.LENGTH_SHORT).show()
         return super.onSupportNavigateUp()
     }*/

    // 액션바의 메뉴 구성, 코드 부분 참고.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuItem1 : MenuItem? = menu?.add(0, 0, 0, "메뉴1")
        val menuItem2 : MenuItem? = menu?.add(0, 1, 0, "메뉴2 ")
        return super.onCreateOptionsMenu(menu)
    }

    //메뉴가 선택되었을 때, 이벤트 처리하는 함수 확인.
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        0 -> {
            Toast.makeText(this@MainActivity,"메뉴1 클릭했음.",Toast.LENGTH_SHORT).show()
            true
        }
        1 -> {
            val intent = Intent(this@MainActivity, ThreeActivity::class.java)
            //단순 화면 전환으로만 사용하니까, 기본 startActivity 사용
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //코드로 업버튼 생성하기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        //버튼 클릭 시 화면 전환 방법, 미리 인텐트를 이용하구요. 구체적인 데이터 전달은 13장에서 이야기하기

        binding.btn1.setOnClickListener{
            val intent = Intent(this@MainActivity, TwoActivity::class.java)
            //단순 화면 전환으로만 사용하니까, 기본 startActivity 사용
            startActivity(intent)
        }
    }
}