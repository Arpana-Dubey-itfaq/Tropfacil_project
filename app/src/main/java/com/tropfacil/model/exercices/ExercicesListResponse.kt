package com.tropfacil.model.exercices


import com.google.gson.annotations.SerializedName
import com.tropfacil.network.BaseResponse

data class ExercicesListResponse(
    @SerializedName("exercices")
    val exercices: List<ExercicesInfoList>
): BaseResponse()