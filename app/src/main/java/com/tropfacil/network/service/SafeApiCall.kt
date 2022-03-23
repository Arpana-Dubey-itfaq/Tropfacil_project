package com.tropfacil.network.service

import com.tropfacil.data.Theme
import com.tropfacil.model.Login_resoponse
import com.tropfacil.network.BaseResponse
import okhttp3.ResponseBody

sealed class SafeApiCall {
    class Success(val data: BaseResponse) : SafeApiCall()
    class Error(val exception: String) : SafeApiCall()
    class Successhome(val data: List<Theme>) : SafeApiCall()
    class SuccessLogin(val data: Login_resoponse) : SafeApiCall()
    class SuccessResponseBody(val data: ResponseBody) : SafeApiCall()
    object Loading : SafeApiCall()
    object Empty : SafeApiCall()
}