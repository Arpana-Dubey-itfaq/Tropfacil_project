package com.tropfacil.model.badges

import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

/**
 * Created by Nimesh Patel on 30-03-2022.
 */
class BadgeListResponse (
    @SerializedName("badges")
    val badges: List<Any>,
):BaseResponse()