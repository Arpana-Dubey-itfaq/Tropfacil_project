package com.tropfacil.network.service

import com.example.example.Themes
import com.tropfacil.network.BaseResponse

sealed class SafeApiCall {
    class Success(val data: BaseResponse) : SafeApiCall()
    class Error(val exception: String) : SafeApiCall()
    class Successhome(val data: ArrayList<Themes>) : SafeApiCall()

    object Loading : SafeApiCall()
    object Empty : SafeApiCall()
}