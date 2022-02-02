package com.tropfacil.network

import com.google.gson.annotations.SerializedName

open class BaseResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("error_description")
    val error_description: String = "",

    @field:SerializedName("modelError")
    val modelError: Any? = null,

    @field:SerializedName("statusCode")
    val statusCode: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("responseCode")
    val responseCode: Int? = null
)