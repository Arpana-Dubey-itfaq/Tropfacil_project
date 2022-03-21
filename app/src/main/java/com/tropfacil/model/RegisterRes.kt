package com.tropfacil.model

import com.google.gson.annotations.SerializedName

data class RegisterRes (
    @SerializedName("success" ) var success : String? = null,
    @SerializedName("error"   ) var error   : Error?  = Error()

)











