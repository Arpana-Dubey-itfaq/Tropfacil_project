package com.tropfacil.ui.nav.account

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_USER_IDNEW
import com.tropfacil.data.provider.PREF_USER_NAME
import com.tropfacil.data.provider.PREF_USER_TOKEN
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.model.UpdatePasswordRequest
import com.tropfacil.network.service.PREF_IS_USER_LOGGED_IN
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Nimesh Patel on 22-03-2022.
 */
class AccountSettingsViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {
    private val updatePasswordStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _updatePasswordStateFlow: StateFlow<SafeApiCall> = updatePasswordStateFlow

    private val updateEmailStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _updateEmailStateFlow: StateFlow<SafeApiCall> = updateEmailStateFlow


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

    fun getUserId(): String {
        return preferenceProvider.getString(PREF_USER_IDNEW, "")
    }

    fun getUserToken(): String {
        return preferenceProvider.getString(PREF_USER_TOKEN, "")

    }

    fun logout() {

        preferenceProvider.clearAllPref()
    }

    fun updateEmail(
        email: String, pwd: String
    ) = launch {
        updateEmailStateFlow.value = SafeApiCall.Loading
        appRepository.updateEmail(getUserId(), email, pwd)
            .catch { e ->
                updateEmailStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                updateEmailStateFlow.value =
                    if (data.responseSuess.equals("true"))
                        SafeApiCall.Success(data)
                    else SafeApiCall.Error("Please try after sometime!")
            }
    }


}