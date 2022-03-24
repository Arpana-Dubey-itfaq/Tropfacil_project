package com.tropfacil.ui.nav.exercices

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.provider.PREF_USER_TOKEN
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.model.exercices.ExercicesInfoList
import com.tropfacil.model.exercices.ExercicesListResponse
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.network.service.SafeApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Nimesh Patel on 23-03-2022.
 */
class ExercicesViewModel (
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {

    private val getExercicesStateFlow =
        MutableStateFlow<SafeApiResponse<ExercicesListResponse>?>(null)
    val _getExercicesStateFlow: StateFlow<SafeApiResponse<ExercicesListResponse>?> =
        getExercicesStateFlow



    fun getExerciseList() = launch {
        getExercicesStateFlow.value = SafeApiResponse.Loading
        val token = preferenceProvider.getString(PREF_USER_TOKEN, "")
        appRepository.getExercices(token)
            .catch { e ->
                getExercicesStateFlow.value = getErrorMessage(e)?.let { SafeApiResponse.Error(e) }
            }.collect { data ->
                getExercicesStateFlow.value = SafeApiResponse.Success(data)
            }
    }

}