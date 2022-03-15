package com.tropfacil.model

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

data class ForgotPasswordRes (
    /* @SerializedName("validationMessages")
    val payload: List<Payload>,*/
    @SerializedName("response")
    val response: Response
) : BaseResponse() {
    /*  data class Payload(
          @SerializedName("message")
          val message: String,
          @SerializedName("subject")
          val subject: String
      )*/

    data class Response(
        @SerializedName("success")
        val success: String
    )
}

