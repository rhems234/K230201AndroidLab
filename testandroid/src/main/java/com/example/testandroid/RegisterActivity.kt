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
    lateinit var filePath:String
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
           Log.d("lsy", "회원가입 버튼 클릭")

           val mail = binding.signmail.text.toString()
           val pw = binding.signPW.text.toString()
           val pw_re = binding.signPW2.text.toString()

           if(mail.isEmpty() || pw.isEmpty() || pw.isEmpty()){
               isExistBlank = true
           }
           else{
               if(pw == pw_re){
                   isPWSame = true
               }
           }
           if(!isExistBlank && isPWSame){

               // 회원가입 성공 토스트 메세지 띄우기
               Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

               // 유저가 입력한 id, pw를 쉐어드에 저장한다.
               val sharedPreference = getSharedPreferences("file name", Context.MODE_PRIVATE)
               val editor = sharedPreference.edit()
               editor.putString("mail", mail)
               editor.putString("pw", pw)
               editor.apply()

               // 로그인 화면으로 이동
               val intent = Intent(this, LoginActivity::class.java)
               startActivity(intent)

           }
           else{
               // 상태에 따라 다른 다이얼로그 띄워주기
               if(isExistBlank){   // 작성 안한 항목이 있을 경우
                   dialog("blank")
               }
               else if(!isPWSame){ // 입력한 비밀번호가 다를 경우
                   dialog("not same")
               }
           }
       }
    }

    private fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)

        // 작성 안한 항목이 있을 경우
        if(type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
        }
        // 입력한 비밀번호가 다를 경우
        else if(type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
        }

        val dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d("lsy", "다이얼로그")
                }
            }
        }

        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()
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