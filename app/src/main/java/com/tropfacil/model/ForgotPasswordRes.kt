package com.tropfacil.model

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

data class ForgotPasswordRes (
            @SerializedName("success")
        val success: String
    )


