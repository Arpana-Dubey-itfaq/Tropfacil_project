package com.example.example

import com.google.gson.annotations.SerializedName


data class Parcours (

  @SerializedName("id"          ) var id          : String?             = null,
  @SerializedName("image"       ) var image       : String?             = null,
  @SerializedName("type"        ) var type        : String?             = null,
  @SerializedName("libelle"     ) var libelle     : String?             = null,
  @SerializedName("couleur"     ) var couleur     : String?             = null,
  @SerializedName("description" ) var description : String?             = null,
  @SerializedName("elements"    ) var elements    : ArrayList<Elements> = arrayListOf()

)