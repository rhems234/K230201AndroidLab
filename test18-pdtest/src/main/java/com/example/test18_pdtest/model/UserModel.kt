package com.example.test18_pdtest.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class UserModel(
    /*var id: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String,
    // email 정보를 추가로 받아오기 같이 해보기.
    var email: String,
    var avatarBitmap: Bitmap*/

    @SerializedName("MAIN_IMG_THUMB")
    var mainImgThumb : String,
    @SerializedName("TITLE")
    var title : String,

    // 공공데이터에서 요청할 파라미터 이름.
    // MAIN_IMG_THUMB, TITLE
)