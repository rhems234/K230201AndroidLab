package com.example.k0201_pjs_test.model

import com.google.gson.annotations.SerializedName

data class PageListModel (
    //var data: List<ItemModel>?
    var getAttractionKr: getAttractionKr
)

data class getAttractionKr (
    var item : List<ItemModel>
)

data class ItemModel (
    @SerializedName("MAIN_TITLE")
    var MAIN_TITLE: String,
    @SerializedName("MAIN_IMG_NORMAL")
    var MAIN_IMG_NORMAL: String,
    @SerializedName("USAGE_AMOUNT")
    var USAGE_AMOUNT: String
)