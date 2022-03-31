package com.tropfacil.model

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(

    @field:SerializedName("token")
    var token: String? = null,

    @field:SerializedName("login")
    var login: String? = null,

    @field:SerializedName("npwd")
    var newPassword: String? = null,

    @field:SerializedName("code_unique ")
    var confirmPassword: String? = null
)

