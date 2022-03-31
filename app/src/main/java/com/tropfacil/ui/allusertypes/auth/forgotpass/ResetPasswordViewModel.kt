package com.tropfacil.ui.allusertypes.auth.forgotpass

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_USER_NAME
import com.tropfacil.data.provider.PREF_USER_TOKEN
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.model.ResetPasswordRequest
import com.tropfacil.model.UpdatePasswordRequest
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {
    private val updatePasswordStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _updatePasswordStateFlow: StateFlow<SafeApiCall> = updatePasswordStateFlow


    private val updatePasswordStateFlow1: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _updatePasswordStateFlow1: StateFlow<SafeApiCall> = updatePasswordStateFlow1


    fun updatePassword(
        updatePasswordRequest: UpdatePasswordRequest,
    ) = launch {
        updatePasswordStateFlow.value = SafeApiCall.Loading
        val token = preferenceProvider.getString(PREF_USER_TOKEN, "")
        val username = preferenceProvider.getString(PREF_USER_NAME, "")
        updatePasswordRequest.token = token
        updatePasswordRequest.login = username
        appRepository.updatePassword(updatePasswordRequest)
            .catch { e ->
                updatePasswordStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                updatePasswordStateFlow.value = SafeApiCall.Success(data)
            }
    }


    fun resetPassword(
        np: String, cp: String, otp: String
    ) = launch {
        updatePasswordStateFlow1.value = SafeApiCall.Loading
        val token = preferenceProvider.getString(PREF_USER_TOKEN, "")
        val username = preferenceProvider.getString(PREF_USER_NAME, "")

        appRepository.resetPassword(token,np,cp,otp)
            .catch { e ->
                updatePasswordStateFlow1.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                updatePasswordStateFlow1.value =if (data.responseSuess.equals("true"))
                     SafeApiCall.Success(data)
                else SafeApiCall.Error("Something went wrong. Please try after sometime!")
            }
    }


    fun logout() {
        preferenceProvider.clearAllPref()
    }
}