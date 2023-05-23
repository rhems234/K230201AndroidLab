package com.example.test18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test18.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    // 리사이클러뷰 작업.
    // 참고 코드, 원본 소스 코드, test18 -> retrofit2 -> MyAdapter.kt, Retrofit 참고.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}