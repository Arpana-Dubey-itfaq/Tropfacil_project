package com.tropfacil.network.request

import com.google.gson.annotations.SerializedName

data class LogoutRequest(

    @field:SerializedName("authToken")
    val authToken: String? = null,

    @field:SerializedName("deviceFCMToken")
    val deviceFCMToken: String? = null,

    @field:SerializedName("devicePlatform")
    val devicePlatform: Int? = null,

    @field:SerializedName("userId")
    val userId: String? = null
)
