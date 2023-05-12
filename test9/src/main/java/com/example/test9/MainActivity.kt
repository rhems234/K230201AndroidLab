package com.example.test9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리소스 폴더에서 values 폴더 하위에 Strings.xml의 속성값을 사용하는 형식만 참고.
        binding.textView1.text = getString(R.string.test)
    }
}