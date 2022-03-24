package com.tropfacil.ui.nav.exercices

import com.tropfacil.base.BaseViewModel
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
class ExercicesViewModel (
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository,
) : BaseViewModel() {
    private val getExercicesStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _getExercicesStateFlow: StateFlow<SafeApiCall> = getExercicesStateFlow

    fun getExerciseList() = launch {
        getExercicesStateFlow.value = SafeApiCall.Loading
        val token = preferenceProvider.getString(PREF_USER_TOKEN, "")
        appRepository.getExercices(token)
            .catch { e ->
                getExercicesStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                getExercicesStateFlow.value = SafeApiCall.Success(data)
            }
    }

}