package com.tropfacil.ui.allusertypes.auth.login




import android.util.Log
import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.*
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.request.LoginRequest
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
                Log.e("message",e.message.toString())
                loginStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                Log.e("message111",data.success)
               preferenceProvider.saveLoginDataToPref(data)

                //loginStateFlow.value = SafeApiCall.Success(data)
            }
    }

  /*  fun loginUsernew(
        loginRequest: LoginRequest
    ) {
        showLoading.value = true
        launch {
            val result =
                withContext(Dispatchers.IO) { appRepository.loginUsernew(loginRequest) }
            showLoading.value = false
            when (result) {
                is SafeApiCallnew.Success -> {
                    preferenceProvider.saveUserInfo(result.data)
                    loginLiveData.value = result.data
                }
                is SafeApiCallnew.Error -> showError.value = getErrorMessage(result.exception)
            }
        }
    }*/




}