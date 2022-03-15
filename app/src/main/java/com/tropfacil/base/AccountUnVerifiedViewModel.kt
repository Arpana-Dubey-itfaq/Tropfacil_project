package com.tropfacil.base

import androidx.lifecycle.MutableLiveData

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.*
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.BaseResponse
import com.tropfacil.network.request.AddressListResponse
import com.tropfacil.network.request.LogoutRequest
import com.tropfacil.network.service.SafeApiCall

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AccountUnVerifiedViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository
) : BaseViewModel() {

    val logoutLiveData = MutableLiveData<BaseResponse>()
    val allDeliveryAddressBookLiveData = MutableLiveData<AddressListResponse>()


    /*fun callLogoutApi(logoutRequest: LogoutRequest) {
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
    }*/


   /* fun getAllDeliveryAddressBookAsync(
    ) {
        showLoading.value = true
        launch {
            val result =
                withContext(Dispatchers.IO) { appRepository.getDeliveryAddressBookAsync(0) }
            showLoading.value = false
            when (result) {
                is SafeApiCall.Success ->
                    allDeliveryAddressBookLiveData.value = result.data
                is SafeApiCall.Error -> showError.value = getErrorMessage(result.exception)
            }
        }
    }*/
    fun getUserID(): String {
        return preferenceProvider.getString(PREF_USER_ID, "")
    }


    fun getUserToken(): String {
        return preferenceProvider.getString(PREF_USER_TOKEN, "")
    }

    fun getFCMToken(): String {
        return preferenceProvider.getString(PREF_USER_FCM_TOKEN, "")
    }


    fun clearAllPref() {
        preferenceProvider.clearAllPref()
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
}
