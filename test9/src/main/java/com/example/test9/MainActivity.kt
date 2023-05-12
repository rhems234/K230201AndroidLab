package com.example.test9

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowMetrics
import com.example.test9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리소스 폴더에서 values 폴더 하위에 Strings.xml의 속성값을 사용하는 형식만 참고.
        // binding.textView1.text = getString(R.string.test)

        // 30 버전을 기준으로 해당 기종의 사이즈 크기를 보는 함수 방법이 조금 다름.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            val windowMetrics : WindowMetrics = windowManager.currentWindowMetrics
            Log.d("lsy", "width : ${windowMetrics.bounds.width()}," +
                    "height : ${windowMetrics.bounds.height()}")
        } else {
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display?.getRealMetrics(displayMetrics)
            Log.d("lsy", "width : ${displayMetrics.widthPixels}," +
                    "height : ${displayMetrics.heightPixels}")
        }
    }
}