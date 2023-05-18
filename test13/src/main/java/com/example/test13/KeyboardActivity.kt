package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.test13.databinding.ActivityKeyboardBinding

class KeyboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityKeyboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeyboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 참고 코드 : MainActivity426
        // xml 뷰도 같이 복사, 버튼, 에디트 뷰

        // manager 소프트 키보드 관련 설정 객체
        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.showInputButton.setOnClickListener {
            // requestFocus, 해당 뷰에 커서 가리킴.
            binding.editView.requestFocus()

            manager.showSoftInput(binding.editView, InputMethodManager.SHOW_IMPLICIT)
        }

        binding.hideInputButton.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}