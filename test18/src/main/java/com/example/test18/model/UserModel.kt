package com.example.test18.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class UserModel(
    var id: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String,
    // email 정보를 추가로 받아오기 같이 해보기.
    var email: String,
    var avatarBitmap: Bitmap
)