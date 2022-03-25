package com.tropfacil.ui.allusertypes.auth.signup

import androidx.lifecycle.MutableLiveData
import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.model.RegisterRequest
import com.tropfacil.model.RegisterRes
import com.tropfacil.network.provider.PREF_EMAIL
import com.tropfacil.network.provider.PREF_IDENTIFIER_KEY
import com.tropfacil.network.provider.PREF_PASSWORD
import com.tropfacil.network.provider.PREF_USERID
import com.tropfacil.network.service.SafeApiCall

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository
) : BaseViewModel() {
    val registerLiveData = MutableLiveData<RegisterRes>()

    private val registerStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _registerStateFlow: StateFlow<SafeApiCall> = registerStateFlow


    private val syncItemsStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _syncItemsStateFlow: StateFlow<SafeApiCall> =
        syncItemsStateFlow

    fun registerUser(request: RegisterRequest) = launch {
        registerStateFlow.value = SafeApiCall.Loading

        appRepository.register(request)
            .catch { e ->
                showLoading.value = false
                showError.value = getErrorMessage(e)
                registerStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!

//                registerStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                showLoading.value = false
                preferenceProvider.putString(
                    PREF_EMAIL,
                    request.email
                )
                preferenceProvider.putString(
                    PREF_PASSWORD,
                    request.prenom
                )

              //  registerLiveData.value = data
                registerStateFlow.value = SafeApiCall.SuccessRegister(data)

            }
    }



    fun savePassword(password: String) {
        preferenceProvider.putString(PREF_PASSWORD, password)
    }
}