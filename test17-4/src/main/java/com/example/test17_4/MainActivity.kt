package com.example.test17

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.test17_4.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var file : File

    // 참고 코드 : test17 -> MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // PDF p14 예제 확인
        // 예제 문서 폴더 위치로, 정해진 이름의 상수값 경로 확인 해보기
        //val file2:File? = getExternalFilesDir(null) -> 앞에 기본 경로/본인패키지명/files/
        val file2:File? = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        if (file2 != null) {
            Log.d("lsy", "getExternalFilesDir의 위치 : ${file2.absolutePath}")
        }
        binding.button1.setOnClickListener {
            // 실제 물리 경로에 , 물리 파일을 만드는 작업. 빈 파일.
            file = File(filesDir, "test.txt")
            // 해당 파일에 쓰기 작업
            val writeStream: OutputStreamWriter = file.writer()
            // writeStream 객체에 write
            writeStream.write("hello world")
            writeStream.flush()

//            openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//                it.write("hello world!!".toByteArray())
//            }

        }
        binding.button2.setOnClickListener {
            // IO -> input Output, stream 단어가 없으면, 문자 기분, (문자열)
            // stream이 있으면, 바이트 단위로 작업을 한다.
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("kkang", "$it")
            }

//            openFileInput("test.txt").bufferedReader().forEachLine {
//                Log.d("kkang", "$it")
//            }
        }
    }
}