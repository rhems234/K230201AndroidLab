package com.example.testandroid

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.testandroid.databinding.ActivityLoginBinding
import kotlin.math.log

import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    var DB:DatabaseHelper?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DB = DatabaseHelper(this)

        binding.signin.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.MainPage.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.login!!.setOnClickListener {
            val user = binding.editID.text.toString()
            val pass = binding.ediPassword.text.toString()
            if(user == "" || pass == "") Toast.makeText(
                this@LoginActivity,
                "회원정보를 전부 입력해주세요.",
                Toast.LENGTH_SHORT)
                .show() else {
                    val insert = DB!!.insertData(email = user)
                if (insert == true) {
                    Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "회원정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
