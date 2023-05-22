package com.example.test17_4

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.test17_4.databinding.ActivityMain546Binding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity546 : AppCompatActivity() {

    lateinit var binding: ActivityMain546Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain546Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
             //파일 쓰기
            // SD 외부 저장소
            val file: File = File(getExternalFilesDir(null), "test.txt")
            val writeStream: OutputStreamWriter = file.writer()
            writeStream.write("hello world")
            writeStream.flush()
            // 파일 읽기
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("kkang", "$it")
            }
        }
        binding.button2.setOnClickListener {
            //공용저장소...........
            // projection -> select _ID, DISPLAY_NAME : 특정의 컬럼을 선택을 하는 항목.
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )
            // contentResolver -> 첫번째 매개변수, 공용저장소 위치,
            // 두번째 매개변수 -> 조건, 내가 조회해서 보기위한 컬럼들.
            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )
            // cursor -> 공용저장소에 있는 사진의 이미지의 아이디와 파일명을 불러옵니다.
            // 테이블 형식으로 데이터를 저장해놓음.
            cursor?.let {
                // cursor는 0행부터 대기 -> moveToNext() -> 1행으로 감.
                // 1행 마다, 컬럼의 id, displayname으로 구성.
                while (cursor.moveToNext()) {
                    Log.d("kkang", "_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}")
                    // 공용 저장소 위치의 Uri, 파일의 위치
                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        cursor.getLong(0)
                    )
                    // 파일의 위치에 있는 사진의 파일을 읽는 작업. 바이트 단위.
                    val resolver = applicationContext.contentResolver
                    resolver.openInputStream(contentUri).use { stream ->
                        // stream 객체에서 작업 수행
                        // stream(바이트단위) -> bitmap 타입으로 변경
                        // 해당 뷰에 설정.
                        // 프로젝트 때, 다이어리 일기 작성  CRUD 예제,
                        // base64 인코딩 부분 간단히 설명.
                        // 프로젝트 때 조금 더 구체적으로 설명.
                        // 안드로이드 + 스프링 부트 연동 프로젝트.
                        val option = BitmapFactory.Options()
                        option.inSampleSize = 10
                        val bitmap = BitmapFactory.decodeStream(stream, null, option)
                        binding.resultImageView.setImageBitmap(bitmap)
                    }
                }
            }
        }

    }
}