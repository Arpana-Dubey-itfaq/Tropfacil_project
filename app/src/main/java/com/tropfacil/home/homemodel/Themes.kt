package com.example.example

import com.google.gson.annotations.SerializedName


data class Themes (

  @SerializedName("id"         ) var id         : String?             = null,
  @SerializedName("libelle"    ) var libelle    : String?             = null,
  @SerializedName("couleur"    ) var couleur    : String?             = null,
  @SerializedName("icone"      ) var icone      : String?             = null,
  @SerializedName("sousthemes" ) var sousthemes : ArrayList<String>   = arrayListOf(),
  @SerializedName("parcours"   ) var parcours   : ArrayList<Parcours> = arrayListOf()

)