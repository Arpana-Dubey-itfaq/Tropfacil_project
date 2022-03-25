package com.tropfacil.rate.viewmodel

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_USER_IDNEW
import com.tropfacil.data.provider.PREF_USER_NAME
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.service.SafeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RateCourseViewModel (
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {

    private val updateUserStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _updateUserStateFlow: StateFlow<SafeApiCall> = updateUserStateFlow

    private val profilePictureStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _profilePictureStateFlow: StateFlow<SafeApiCall> = profilePictureStateFlow

    fun SendRating(
    ) = launch {
        updateUserStateFlow.value = SafeApiCall.Loading
        val id = preferenceProvider.getString(PREF_USER_IDNEW, "")
        val nom = preferenceProvider.getString(PREF_USER_NAME, "")
       /* appRepository.SendRating(id, nom,)
            .catch { e ->
                updateUserStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                updateUserStateFlow.value = SafeApiCall.Success(data)
            }*/
    }
}