package com.tropfacil.network.auth.register

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse


data class AllRegisterResponse(

    @field:SerializedName("payload")
    val payload: RegisterPayload? = null
) : BaseResponse()

data class RegisterPayload(

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
    val lastEditedBy: Any? = null,

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
    val lastEditedDate: Any? = null,

    @field:SerializedName("isEnabledBy")
    val isEnabledBy: String? = null,

    @field:SerializedName("dateEnabled")
    val dateEnabled: String? = null,

    @field:SerializedName("professionalID")
    val professionalID: Any? = null,

    @field:SerializedName("username")
    val username: Any? = null,

    @field:SerializedName("methodDelivery")
    val methodDelivery: Int? = null
)
