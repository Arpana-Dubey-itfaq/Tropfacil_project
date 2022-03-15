package com.example.example

import com.google.gson.annotations.SerializedName


data class Elements (

  @SerializedName("id"      ) var id      : String? = null,
  @SerializedName("type"    ) var type    : String? = null,
  @SerializedName("libelle" ) var libelle : String? = null

)