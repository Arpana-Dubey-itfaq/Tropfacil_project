package com.tropfacil.model

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

data class RegisterRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("nom")
    val nom: String,
    @SerializedName("prenom")
    val prenom: String,
    @SerializedName("civilite")
    val civilite: String
)

