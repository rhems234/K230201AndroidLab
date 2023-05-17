package com.example.testandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.testandroid.databinding.ActivityIntroBinding
import com.example.testandroid.databinding.ActivityMainBinding

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent) //인트로 실행 후 바로 MainActivity로 넘어감.
            finish()
        }, 3000) //1초 후 인트로 실행
    }
}