package com.tropfacil.ui.allusertypes.auth.forgotpass


import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val forgotPasswordStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _forgotPasswordStateFlow: StateFlow<SafeApiCall> = forgotPasswordStateFlow

    fun forgotPassword(email: String) = launch {
        forgotPasswordStateFlow.value = SafeApiCall.Loading
        appRepository.forgotPassword(email)
            .catch { e ->
                forgotPasswordStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                forgotPasswordStateFlow.value = if (data.success.equals("true"))
                    SafeApiCall.SuccessForgot(data)
                else SafeApiCall.Error("Something went wrong. Please try after sometime!")
            }
    }
}