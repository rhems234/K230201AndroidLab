package com.example.testandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroid.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.signName
        val email = binding.signmail
        val pw = binding.signPW
        val pw2 = binding.signPW2

        binding.back.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.pwcheckbutton.setOnClickListener {
            if (pw.getText().toString().equals(pw2.getText().toString())) {
                Toast.makeText(this@RegisterActivity, "비밀번호 일치", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@RegisterActivity, "비밀번호 일치하지 않음", Toast.LENGTH_SHORT).show()
            }
        }

       binding.signupbutton.setOnClickListener {
            if (name.getText().toString().length == 0 || email.getText().toString().length == 0 || pw.getText().toString().length == 0) {
                Toast.makeText(this@RegisterActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@RegisterActivity, "회원가입 성공.", Toast.LENGTH_SHORT).show()
            }
       }
    }
}