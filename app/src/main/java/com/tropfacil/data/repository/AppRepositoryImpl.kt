package com.tropfacil.data.repository


import com.tropfacil.network.BaseResponse
import com.tropfacil.network.auth.account.RefreshTokenResponse
import com.tropfacil.network.request.LogoutRequest
import com.tropfacil.network.request.RegenerateTokenRequest
import com.tropfacil.network.service.ApiService
import com.tropfacil.network.service.SafeApiCall
import okhttp3.RequestBody

class AppRepositoryImpl(
    private val apiService: ApiService
) : AppRepository {
   /* override suspend fun setFCMToken(fcmToken: String, deviceType: Int): SafeApiCall<BaseResponse> {
        TODO("Not yet implemented")
    }*/


    /* override suspend fun setFCMToken(fcmToken: String, deviceType: Int): SafeApiCall<BaseResponse> {
         return try {
             val result = apiService.setFCMToken(fcmToken, deviceType).await()
             SafeApiCall.Success(result)
         } catch (ex: Throwable) {
             SafeApiCall.Error(ex)
         }
     }*/

    override suspend fun reGenerateTokensAsync(regenerateTokenRequest: RegenerateTokenRequest): SafeApiCall<RefreshTokenResponse> {
        return try {
            val result = apiService.reGenerateTokensAsync(regenerateTokenRequest).await()
            SafeApiCall.Success(result)
        } catch (ex: Throwable) {
            SafeApiCall.Error(ex)
        }
    }


    override suspend fun logoutAsync(logoutRequest: LogoutRequest): SafeApiCall<BaseResponse> {
        return try {
            val result = apiService.logoutAsync(logoutRequest).await()
            SafeApiCall.Success(result)
        } catch (ex: Throwable) {
            SafeApiCall.Error(ex)
        }
    }

  }