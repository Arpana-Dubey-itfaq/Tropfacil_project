package com.tropfacil.ui.allusertypes.auth.login


import android.util.Log
import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.*
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.PREF_IS_USER_LOGGED_IN
import com.tropfacil.network.service.SafeApiCall

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val loginStateFlow: MutableStateFlow<SafeApiCall> = MutableStateFlow(SafeApiCall.Empty)
    val _loginStateFlow: StateFlow<SafeApiCall> = loginStateFlow

    private val syncItemsStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _syncItemsStateFlow: StateFlow<SafeApiCall> =
        syncItemsStateFlow


    fun loginUser(loginRequest: LoginRequest) = launch {
        loginStateFlow.value = SafeApiCall.Loading
        appRepository.login(loginRequest)
            .catch { e ->
                loginStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                if (data.success.equals("true")) {
                    data.username = loginRequest.loginName
                    preferenceProvider.saveLoginDataToPref(data)
                    loginStateFlow.value = SafeApiCall.SuccessLogin(data)
                    //loginStateFlow.value = SafeApiCall.Success(data)
                } else {
                    loginStateFlow.value =  if (data.error != null) {
                          SafeApiCall.Error(data.error?.message.toString())
                    } else SafeApiCall.Error("Something went wrong. Please try after sometime!")
                }
            }
    }

    fun stayLogin(isLoggedIn:Boolean) {
        preferenceProvider.putBoolean(PREF_IS_USER_LOGGED_IN, isLoggedIn)
    }
}
