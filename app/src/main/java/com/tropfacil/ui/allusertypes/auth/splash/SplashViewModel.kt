package com.tropfacil.ui.allusertypes.auth.splash

import androidx.lifecycle.MutableLiveData
import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_CUSTOMER_USER_IS_LOGGED_IN
import com.tropfacil.data.provider.PREF_DELIVERY_MAN_USER_IS_LOGGED_IN
import com.tropfacil.data.provider.PREF_HEALTH_PROF_USER_IS_LOGGED_IN
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.BaseResponse
import com.tropfacil.network.auth.account.RefreshTokenResponse
import com.tropfacil.network.request.LogoutRequest
import com.tropfacil.network.request.RegenerateTokenRequest
import com.tropfacil.network.service.SafeApiCall

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository
) : BaseViewModel() {

    val logoutLiveData = MutableLiveData<BaseResponse>()
    val refreshTokenLiveData = MutableLiveData<RefreshTokenResponse>()


    fun callLogoutApi(logoutRequest: LogoutRequest) {
        showLoading.value = true
        launch {
            val result =
                withContext(Dispatchers.IO) { appRepository.logoutAsync(logoutRequest) }
            showLoading.value = false
            when (result) {
                is SafeApiCall.Success -> logoutLiveData.value =
                    result.data
                is SafeApiCall.Error -> showError.value = getErrorMessage(result.exception)
            }
        }
    }

    fun callRefreshTokenApi(refreshTokenRequest: RegenerateTokenRequest) {
        showLoading.value = true
        launch {
            val result =
                withContext(Dispatchers.IO) {
                    appRepository.reGenerateTokensAsync(
                        refreshTokenRequest
                    )
                }
            showLoading.value = false
            when (result) {
                is SafeApiCall.Success -> {
                    preferenceProvider.saveRefreshTokenUserInfo(result.data)
                    refreshTokenLiveData.value = result.data
                }
                is SafeApiCall.Error -> showError.value = getErrorMessage(result.exception)
            }
        }
    }

    fun saveUserLoggedIn() {
        preferenceProvider.putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, false)
    }

    fun isUserLoggedIn(): Boolean {
        return preferenceProvider.getBooleanDefaultFalse(PREF_CUSTOMER_USER_IS_LOGGED_IN)
    }

    fun isHealthProfUserLoggedIn(): Boolean {
        return preferenceProvider.getBooleanDefaultFalse(PREF_HEALTH_PROF_USER_IS_LOGGED_IN)
    }

    fun isDeliveryManUserLoggedIn(): Boolean {
        return preferenceProvider.getBooleanDefaultFalse(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN)
    }

    fun clearAllPref() {
        preferenceProvider.clearAllPref()
    }
}