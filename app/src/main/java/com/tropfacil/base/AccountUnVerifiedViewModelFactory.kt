package com.tropfacil.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository


class AccountUnVerifiedViewModelFactory(
    val preferenceProvider: PreferenceProvider,
    val appRepository: AppRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountUnVerifiedViewModel(
            preferenceProvider,
            appRepository
        ) as T
    }
}