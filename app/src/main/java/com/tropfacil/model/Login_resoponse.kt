package com.tropfacil.model

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse


data class Login_resoponse(


        @SerializedName("success")
        val success: String,
        @SerializedName("pf")
        val pf: String,
        @SerializedName("nom")
        val nom: String,
        @SerializedName("prenom")
        val prenom: String,
        @SerializedName("date_debut")
        val date_debut: String,
        @SerializedName("date_fin")
        val date_fin: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("username")
        var username: String?="",
    )





