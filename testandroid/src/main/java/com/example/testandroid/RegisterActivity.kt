package com.example.testandroid

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.testandroid.databinding.ActivityRegisterBinding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    var DB:DatabaseHelper?=null
    lateinit var filePath:String
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DB = DatabaseHelper(this)

        val requestCamerFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {

            val calRatio = calculateInSampleSize(
                // 저장할 물리 파일의 위치를 Uri 타입으로 변경.
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
        }

        binding.cameraButton.setOnClickListener {
            val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir:File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
            "JPEG_${timeStamp}",
            ".jpg",
            storageDir
            )
            filePath = file.absolutePath

            val photoURI : Uri = FileProvider.getUriForFile(
                this,
                "com.example.testandroid.fileprovider",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCamerFileLauncher.launch(intent)
        }


        // 갤러리 버튼
        binding.galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        val name = binding.signName
        val email = binding.signmail
        val pw = binding.signPW
        val pw2 = binding.signPW2

        // 뒤로가기
        binding.back.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // 비밀번호 확인
        binding.pwcheckbutton.setOnClickListener {
            if (pw.getText().toString().equals(pw2.getText().toString())) {
                Toast.makeText(this@RegisterActivity, "비밀번호 일치", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@RegisterActivity, "비밀번호 일치하지 않음", Toast.LENGTH_SHORT).show()
            }
        }

        // 회원가입 버튼
       binding.signupbutton.setOnClickListener {
           val name = binding.signName.text.toString()
           val email = binding.signmail.text.toString()
           val password = binding.signPW.text.toString()
           val repass = binding.signPW2.text.toString()
           val phone = binding.signPhone.text.toString()

           if(name == "" || email == "" || password == "" || phone =="") Toast.makeText(
               this@RegisterActivity,
               "회원정보를 전부 입력해주세요.",
               Toast.LENGTH_SHORT
           ).show() else {
               if(password == repass) {
                   val checkUsername = DB!!.insertData(name,email,password,phone)
                   if (checkUsername == false) {
                       val insert = DB!!.insertData(name, email, password, phone)
                       if(insert == true){
                           Toast.makeText(this@RegisterActivity, "가입되었습니다.",Toast.LENGTH_SHORT).show()
                           val intent = Intent(applicationContext, LoginActivity::class.java)
                           startActivity(intent)
                       } else {
                           Toast.makeText(this@RegisterActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                       }
                   } else{
                       Toast.makeText(this@RegisterActivity, "이미 가입된 회원입니다.", Toast.LENGTH_SHORT).show()
                   }
               } else {
                   Toast.makeText(this@RegisterActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
               }
           }
       }
    }



    private fun calculateInSampleSize(fileUri: Uri, reqWidth:Int, reqHeight:Int):Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        try{
            var inputStream = contentResolver.openInputStream(fileUri)

            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e:Exception){
            e.printStackTrace()
        }
        val(height:Int, width:Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if(height > reqHeight || width > reqWidth){
            val halfHeight : Int = height / 2
            val halfWidth : Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth){
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}