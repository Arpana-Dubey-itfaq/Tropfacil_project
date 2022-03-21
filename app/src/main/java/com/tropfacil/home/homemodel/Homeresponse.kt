package com.example.example

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

data class Homeresponse(

  @SerializedName("success" ) var success : String,
  @SerializedName("themes"  ) var themes  : ArrayList<Themes> = arrayListOf()

)