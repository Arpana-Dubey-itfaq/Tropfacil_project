package com.tropfacil.network.request

import com.google.gson.annotations.SerializedName

data class RegenerateTokenRequest(

	@field:SerializedName("refreshTokenExpiration")
	var refreshTokenExpiration: String? = null,

	@field:SerializedName("dateCreated")
	var dateCreated: String? = null,

	@field:SerializedName("tokenExpiration")
	var tokenExpiration: String? = null,

	@field:SerializedName("userLoginRecordID")
	var userLoginRecordID: Int? = null,

	@field:SerializedName("accountType")
	var accountType: Int? = null,

	@field:SerializedName("isActive")
	var isActive: Boolean? = null,

	@field:SerializedName("userId")
	var userId: String? = null,

	@field:SerializedName("device")
	var device: Int? = null,

	@field:SerializedName("token")
	var token: String? = null,

	@field:SerializedName("refreshToken")
	var refreshToken: String? = null
)
