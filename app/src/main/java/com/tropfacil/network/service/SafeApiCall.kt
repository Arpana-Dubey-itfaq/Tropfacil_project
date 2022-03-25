package com.tropfacil.network.service

import com.tropfacil.data.Theme
import com.tropfacil.data.home_response
import com.tropfacil.model.ForgotPasswordRes
import com.tropfacil.model.Login_resoponse
import com.tropfacil.model.Register_response
import com.tropfacil.network.BaseResponse
import okhttp3.ResponseBody

sealed class SafeApiCall {
    class Success(val data: BaseResponse) : SafeApiCall()
    class Error(val exception: String) : SafeApiCall()
    class Successhome(val data: home_response) : SafeApiCall()
    class SuccessLogin(val data: Login_resoponse) : SafeApiCall()
    class SuccessRegister(val data: Register_response) : SafeApiCall()
    class SuccessForgot(val data: ForgotPasswordRes) : SafeApiCall()

    class SuccessResponseBody(val data: ResponseBody) : SafeApiCall()
    object Loading : SafeApiCall()
    object Empty : SafeApiCall()
}