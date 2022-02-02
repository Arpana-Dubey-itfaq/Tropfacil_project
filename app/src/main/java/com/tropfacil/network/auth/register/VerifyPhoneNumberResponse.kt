package com.tropfacil.network.auth.register

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse


data class VerifyPhoneNumberResponse(

	@field:SerializedName("payload")
	val payload: Boolean? = null
): BaseResponse()