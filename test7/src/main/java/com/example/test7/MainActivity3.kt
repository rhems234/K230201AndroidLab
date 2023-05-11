package com.example.test7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.test7.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain3Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main3)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            binding.btn1.visibility = View.INVISIBLE
            binding.img1.visibility = View.VISIBLE
        }

        binding.img1.setOnClickListener {
            binding.btn1.visibility = View.VISIBLE
            binding.img1.visibility = View.INVISIBLE
        }

    }
}