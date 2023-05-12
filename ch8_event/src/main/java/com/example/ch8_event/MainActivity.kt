package com.example.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 뒤로가기 버튼을 누른 시각을 저장하는 속성
    var initTime = 0L

    // 멈춘 시각을 저장하는 속성
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //    setContentView(R.layout.activity_main)

        binding.start.setOnClickListener {
            binding.watch.base = SystemClock.elapsedRealtime() + pauseTime
            binding.watch.start()

            // 버튼 표시 여부 조정
            binding.start.isEnabled = false
            binding.stop.isEnabled = true
            binding.reset.isEnabled = true
        }

        binding.stop.setOnClickListener {
            pauseTime = binding.watch.base - SystemClock.elapsedRealtime()
            binding.watch.stop()
            binding.start.isEnabled = true
            binding.stop.isEnabled = true
            binding.reset.isEnabled = false
        }

        binding.reset.setOnClickListener {
            pauseTime = binding.watch.base - SystemClock.elapsedRealtime()
            binding.watch.stop()
            binding.start.isEnabled = true
            binding.stop.isEnabled = false
            binding.reset.isEnabled = false
        }
    }

    // 뒤로가기 버튼 이벤트 핸들러
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        // 뒤로가기 버튼을 눌렸을 때 처리
        if(keyCode === KeyEvent.KEYCODE_BACK){

            // 뒤로가기 버튼을 처음 눌렀거나 누른 지 3초가 지났을 때 처리
            if(System.currentTimeMillis() - initTime > 30000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT)
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}