package com.tropfacil.network.request

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

import java.io.Serializable

data class AddressListResponse(

    @field:SerializedName("payload")
    val payload: List<AddressListPayloadItem>? = null
) : BaseResponse()

data class AddressListPayloadItem(

    @field:SerializedName("userAddressID")
    val userAddressID: Int? = null,

    @field:SerializedName("addressName")
    val addressName: String? = null,

    @field:SerializedName("isCurrentAddress")
    val isCurrentAddress: Boolean? = null,

    @field:SerializedName("completeAddress")
    val completeAddress: String? = null,

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
) : Serializable
