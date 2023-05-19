package com.example.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test16.databinding.ActivityMain2Binding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity2 : AppCompatActivity() {

    lateinit var binding : ActivityMain2Binding
    lateinit var filePath: String

    // 에뮬레이터 실제 물리 경로
    // storage -> emulated -> 0 -> Android -> data -> com.example.test16 -> files -> Pictures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 순서 2
        // 카메라 앱 촬영 후 사진 재활용.
        //camera request launcher.................
        // 카메라에서 넘어온 사진 데이터를 처리해서,
        // 앱별 저장소(우리가 지정한, Pictures에 파일로 저장 작업)
        val requestCameraFileLauncher = registerForActivityResult(
            // 후 처리.
            ActivityResultContracts.StartActivityForResult()) {
            // 원본 사진 크기 그대로 읽거나 처리 작업하면, OOM 사이즈 줄여야한다.

            // 갤러리 설명했음.
            val calRatio = calculateInSampleSize(
                // 저장할 물리 파일의 위치를 Uri 타입으로 변경.
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            // 갤러리 주석 참고.
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        // 갤러리 앱에서 사진 선택 후, 후처리 하기.
        // 참고 코드 : ch16_provider -> MainActivity,
        // 뷰는 재활용. 복사.
        // 순서 1
        //gallery request launcher..................
        // 갤러리 앱에서 사진 선택 한 데이터를 여기서 처리함.
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // it -> 사진 데이터
            try {
                // calRatio -> 결과값은 정수인데, 예를 들어서 4 일때
                // option.inSampleSize = calRatio -> 의미가, 1/4 비율로 줄인다.
                // 특정 값의 비율을 임의로 정해도 되지만, 특정 사이즈를 요구한 값이 있으면
                // 그 값에 맞추는 함수를 만들었다.
                val calRatio = calculateInSampleSize(
                    // 실물의 사진을 높이를 반으로 줄이면서,
                    // inSampleSize 사이즈를 줄임
                    it.data!!.data!!,
                    // 정적 res -> values -> dimens.xml 특정 크기를 정해서 재사용했다.
                    // 그냥 하드코딩으로 150dp 주어도 상관 없음.
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                // BitmapFactory 사진을 읽는 업무, Option()를 통해서, 크기를 지정.
                val option = BitmapFactory.Options()
                //
                option.inSampleSize = calRatio

                // inputStream : 사진을 바이트로 읽은 데이터가 있다.
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                // 바이트 타입 -> bitmap 타입 형식으로 사진의 타입을 변경.
                // 사진의 결과는 원본 사이즈가 줄어서, bitmap 타입으로 변경 되었다.
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    // 이제 드디어 원하는 사진을 내가 원하는 크기에 맞게끔 할당.
                    // bitmap : 리사이즈가 된 사진이 들어 있다.
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let{
                    Log.d("kkang", "bitmap null")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        // 시작, 갤러리 버튼을 클릭, 이벤트 발생.
        binding.galleryButton.setOnClickListener {
            //gallery app........................
            // Intent.ACTION_PICK -> 갤러리 앱 호출, 두번째 매개변수의 타입을 보고 판단.
            // 두번째 매개변수의 타입을 보고 판단 :  MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // 속성의 값은 모든 이미지를 의미, MIMEType
            intent.type = "image/*"
            // 후처리 작업 시작. 현재 액티비티 -< 갤러리 앱 : 사진 선택 -> 현재 액티비티 돌아옴.
            // 위에 정의된 requestGalleryLauncher 돌아감.
            requestGalleryLauncher.launch(intent)
        }

        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            // 현재 날짜와 시간을 조합해서 yyyyMMdd_HHmmss, 문자열 형식으로 만들어줌.
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // 실제 저장되는 물리 경로,
            // 앱별 저장소, getExternalFilesDir 이용하면,
            // 개발자가 앱별 저장소에 접근 하기 위해서, 시스템에 요청, 콘텐츠 프로바이더를 이용함.
            // 그 때 , 사용할 경로도 설정을 같이 함.
            // xml -> 경로 설정.
            // DIRECTORY_PICTURES 상수, 즉 갤러리 위치에 물리파일 저장.
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            // 실제 물리 파일을 만드는 작업.
            // 파일명 : JPEG_yyyyyMMdd_HHmmss_브레드.jpg
            // storageDIR : 경로에 물리 파일 만들어짐.
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            // 실제 물리 파일의 위치 주소, 절대 경로.
            filePath = file.absolutePath
            // 컨텐츠 프로바이더를 이용해서 외부 저장 경로에 접근,
            // 암구호 : com.example.ch16_provider.fileprovider 인증이되면,
            // 해당 경로에 접근이 가능하고, 파일 위치 정보도 알아 낼 수 있음.
            // 매니페스트 파일에서 설정1
            // res -> xml 여기서 경로 설정2
            // 복사
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test16.fileprovider",
                file
            )
            // MediaStore.ACTION_IMAGE_CAPTURE : 카메라 앱 호출.
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 카메라 앱에 데이터를 전달, 키 값 : 상수, output 문자열
            // value 값은 : photoURI : context 자기자신 객체, 액티비티
            // 암구호 : com.example.ch16_provider.fileprovider
            // 물리파일 이름.
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            // 후처리 작업. 현재 앱 -> 카메라 앱, 사진 촬영한 사진 데이터 -> 현재 앱으로 가져오기.
            requestCameraFileLauncher.launch(intent)

        }
    }

    // 이미지의 크기를 원본으로 읽으면, OOM(Out of Memory)현상으로 문제가됨.
    // 이미지의 크기를 적당히 조절하는 함수
    // 예를 들어, 현재 뷰ㅢ 가로 세로 150dp, 150dp,
    // 실제 사진의 해상도 : 3000dp, 2000dp, -> 반으로 줄여나감.
    // 1500, 1000 -> 750, 500 -> 375, 250 -> 187.5, 125

    // 첫 매개변수, 사진의 파일 Uri,
    // 두번째 매개변수 : 원하는 사이즈 (현재 우리는 150dp), 가로, 세로
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        // options, BitmapFactory 사진을 처리하는 업무, 그런데, Options가 들어가면
        // 사진 처리 업무보다는, 관련 옵션을 정한다.
        val options = BitmapFactory.Options()
        // inJustDecodeBounds = true가 되면, 본 업무 말고, 옵션만 처리한다.
        options.inJustDecodeBounds = true
        try {
            // contentResolver.openInputStream 선택된 사진을 바이트로 읽어서 바이트로 반환.
            // inputStream : 사진이 바이트 형으로 읽어 놓은 상태.
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        // 읽은 사진의 가로 세로 정보를, 재할당 height, width
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        // 원본 사이즈 그대로, 즉 비율이 줄지 않음.
        var inSampleSize = 1
        //inSampleSize 비율 계산
        // height 불러온 원본 사진의 실제 높이
        // reqHeight : 내가 꾸미는 뷰의 높이 : 150dp
        // 예를 들어 : 원본 사진의 높이 2000
        // 원하는 사이즈 : 150
        if (height > reqHeight || width > reqWidth) {

            // 반으로 줄이기.
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}