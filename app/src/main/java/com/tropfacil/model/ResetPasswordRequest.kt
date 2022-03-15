package com.tropfacil.model

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("pwd")
    val newPassword: String? = null,

    @field:SerializedName("npwd")
    val confirmPassword: String? = null
)

