package com.tropfacil.network.service



import com.tropfacil.network.BaseResponse
import com.tropfacil.network.auth.account.RefreshTokenResponse
import com.tropfacil.network.request.LogoutRequest
import com.tropfacil.network.request.RegenerateTokenRequest
import kotlinx.coroutines.Deferred

import retrofit2.http.*


interface ApiService {



    @GET("User/SetFCMToken")
    fun setFCMToken(
        @Query("FCMToken") fcmToken: String,
        @Query("DeviceType") deliveryType: Int
    ): Deferred<BaseResponse>


    @POST("User/ReGenerateTokens")
    fun reGenerateTokensAsync(@Body regenerateTokenRequest: RegenerateTokenRequest): Deferred<RefreshTokenResponse>

    @POST("User/MobileLogout")
    fun logoutAsync(@Body logoutRequest: LogoutRequest): Deferred<BaseResponse>

}