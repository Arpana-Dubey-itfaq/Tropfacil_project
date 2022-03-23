package com.tropfacil.ui.nav.profile

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_USER_IDNEW
import com.tropfacil.data.provider.PREF_USER_NAME
import com.tropfacil.data.provider.PREF_USER_TOKEN
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Nimesh Patel on 23-03-2022.
 */
class ProfileSettingsViewModel (
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {

    private val updateUserStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _updateUserStateFlow: StateFlow<SafeApiCall> = updateUserStateFlow

    private val profilePictureStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _profilePictureStateFlow: StateFlow<SafeApiCall> = profilePictureStateFlow

    fun updateUser(
        prenom:String?
    ) = launch {
        updateUserStateFlow.value = SafeApiCall.Loading
        val id = preferenceProvider.getString(PREF_USER_IDNEW, "")
            val nom = preferenceProvider.getString(PREF_USER_NAME, "")
        appRepository.updateUser(id,nom,prenom)
            .catch { e ->
                updateUserStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                updateUserStateFlow.value = SafeApiCall.Success(data)
            }
    }
    fun getProfilePicture() = launch {
        profilePictureStateFlow.value = SafeApiCall.Loading
        val token =preferenceProvider.getString(PREF_USER_TOKEN, "")
        appRepository.getProfilePicture(token)
            .catch { e ->
                profilePictureStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                profilePictureStateFlow.value = SafeApiCall.SuccessResponseBody(data)

            }
    }
}