package com.tropfacil.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Nimesh Patel on 22-03-2022.
 */
class UpdatePasswordRequest(

    @field:SerializedName("token")
    var token: String? = null,

 @field:SerializedName("login")
    var login: String? = null,

    @field:SerializedName("pwd")
    var password: String? = null,

    @field:SerializedName("npwd")
    var newPassword: String? = null,
)