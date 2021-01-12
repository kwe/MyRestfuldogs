package com.kwevans.myrestfuldogs

import com.google.gson.annotations.SerializedName

data class Dog (
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("breed")
    val breed: String
)