package com.tropfacil.network

import com.google.gson.annotations.SerializedName

open class BaseResponse(


    val message: String? = null,

    @field:SerializedName("success")
    val responseSuess: String? = null,


)

