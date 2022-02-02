package com.tropfacil.network.auth.login

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

import java.io.Serializable

data class LoginResponse(

    @field:SerializedName("payload")
    val payload: LoginPayload? = null
) : BaseResponse(), Serializable

data class Tokens(

    @field:SerializedName("refreshTokenExpiration")
    val refreshTokenExpiration: String? = null,

    @field:SerializedName("dateCreated")
    val dateCreated: String? = null,

    @field:SerializedName("tokenExpiration")
    val tokenExpiration: String? = null,

    @field:SerializedName("userLoginRecordID")
    val userLoginRecordID: Int? = null,

    @field:SerializedName("accountType")
    val accountType: Int? = null,

    @field:SerializedName("isActive")
    val isActive: Boolean? = null,

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("device")
    val device: Int? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("refreshToken")
    val refreshToken: String? = null
) : Serializable

data class LoginPayload(

    @field:SerializedName("userInfo")
    val userInfo: UserInfo? = null,

    @field:SerializedName("tokens")
    val tokens: Tokens? = null
) : Serializable

data class UserInfo(

    @field:SerializedName("userRecordID")
    val userRecordID: Int? = null,

    @field:SerializedName("lastName")
    val lastName: String? = null,

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("mobileNumber")
    val mobileNumber: String? = null,

    @field:SerializedName("lockedDateTime")
    val lockedDateTime: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: Any? = null,

    @field:SerializedName("isLocked")
    val isLocked: Boolean? = null,

    @field:SerializedName("registrationCode")
    val registrationCode: Any? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("healthNumber")
    val healthNumber: String? = null,

    @field:SerializedName("userFieldID")
    val userFieldID: String? = null,

    @field:SerializedName("accountType")
    val accountType: Int? = null,

    @field:SerializedName("dateOfBirth")
    val dateOfBirth: Any? = null,

    @field:SerializedName("registrationPlatform")
    val registrationPlatform: Int? = null,

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("lastEditedBy")
    val lastEditedBy: String? = null,

    @field:SerializedName("firstName")
    val firstName: String? = null,

    @field:SerializedName("kbis")
    val kbis: String? = null,

    @field:SerializedName("createdDate")
    val createdDate: String? = null,

    @field:SerializedName("createdBy")
    val createdBy: String? = null,

    @field:SerializedName("isEnabled")
    val isEnabled: Boolean? = null,

    @field:SerializedName("lastEditedDate")
    val lastEditedDate: String? = null,

    @field:SerializedName("isEnabledBy")
    val isEnabledBy: String? = null,

    @field:SerializedName("dateEnabled")
    val dateEnabled: String? = null,

    @field:SerializedName("professionalID")
    val professionalID: Any? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("methodDelivery")
    val methodDelivery: Int? = null
) : Serializable
