package com.tropfacil.userstatprofile

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_USER_TOKEN
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.model.badges.BadgeListResponse
import com.tropfacil.model.exercices.ExercicesListResponse
import com.tropfacil.network.BaseResponse
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.network.service.SafeApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Nimesh Patel on 30-03-2022.
 */
class UserStatsProfileViewModel (
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {

    private val getBadgesStateFlow =
        MutableStateFlow<SafeApiResponse<BadgeListResponse>?>(null)
    val _getBadgesStateFlow: StateFlow<SafeApiResponse<BadgeListResponse>?> =
        getBadgesStateFlow

    fun getBadges() = launch {
        getBadgesStateFlow.value = SafeApiResponse.Loading
        val token = preferenceProvider.getString(PREF_USER_TOKEN, "")
        appRepository.getBadges(token)
            .catch { e ->
                getBadgesStateFlow.value = getErrorMessage(e)?.let { SafeApiResponse.Error(e) }
            }.collect { data ->
                getBadgesStateFlow.value = SafeApiResponse.Success(data)
            }
    }
}