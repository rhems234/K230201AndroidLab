package com.example.test16

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.test16.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var requestContactsLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            for (entry in it.entries) {
                if(entry.key == "android.permission.READ_CONTACTS" && entry.value) {
                    Log.d("kkang", "granted ok...")
                    val intent =
                        Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                    requestContactsLauncher.launch(intent)
                }else {
                    Toast.makeText(this, "required permission..", Toast.LENGTH_SHORT).show()
                }
            }

        }

        requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 연락처 접근 승인이 받은 상태이면, 이제 넘어온 연락처 정보를 불러오는 과정.
            if(it.resultCode == RESULT_OK){
                // it.data 이 부분은 선택된 연락처 정보 하나가 있음.
                Log.d("kkang", "${it.data?.data}")

                // 커서
                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        // 연락처 , 사람 이름.
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        // 연락처, 전화번호, 상수로 등록된 문자열.
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null
                )
                Log.d("kkang", "cursor size....${cursor?.count}")
                if (cursor!!.moveToFirst()) {
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContact.text = "name: $name, phone: $phone"
                }
            }
        }
        binding.contactButton.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this,"android.permission.READ_CONTACTS") !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(arrayOf("android.permission.READ_CONTACTS"))
            } else {
                val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestContactsLauncher.launch(intent)
            }
        }
    }
}