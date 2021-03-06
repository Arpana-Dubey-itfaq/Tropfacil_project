package com.tropfacil.home.view

import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.tropfacil.base.BaseViewModel
import com.tropfacil.data.Parcour
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.network.service.SafeApiCall

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val preferenceProvider: PreferenceProvider,
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val loginStateFlow: MutableStateFlow<SafeApiCall> = MutableStateFlow(SafeApiCall.Empty)
    val _loginStateFlow: StateFlow<SafeApiCall> = loginStateFlow

    private val syncItemsStateFlow: MutableStateFlow<SafeApiCall> =
        MutableStateFlow(SafeApiCall.Empty)
    val _syncItemsStateFlow: StateFlow<SafeApiCall> =
        syncItemsStateFlow


    // val parcorselist: MutableLiveData<ArrayList<Parcour>> = MutableLiveData()
    fun HomeData(authorization: String?) = launch {
        syncItemsStateFlow.value = SafeApiCall.Loading
        appRepository.HomeData(authorization)
            .catch { e ->
                Log.e("message Error", e.message.toString())
                syncItemsStateFlow.value = getErrorMessage(e)?.let { SafeApiCall.Error(it) }!!
            }.collect { data ->
                if (data.success.equals("true")) {
                    syncItemsStateFlow.value =        SafeApiCall.Successhome(data)}
                else {
                    syncItemsStateFlow.value =  if (data.error != null) {
                        SafeApiCall.Error(data.error?.message.toString())
                    } else SafeApiCall.Error("Something went wrong. Please try after sometime!")
                }
                    // Log.e("message111", data)
                //  preferenceProvider.saveLoginDataToPref(data)
                //loginStateFlow.value = SafeApiCall.Success(data)
            }
    }

}