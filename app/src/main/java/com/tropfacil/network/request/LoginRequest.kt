package com.tropfacil.network.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(


    @field:SerializedName("login")
    var loginName: String? = null,
    @field:SerializedName("pwd")
    var password: String? = null,

    )
