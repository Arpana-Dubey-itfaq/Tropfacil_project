package com.tropfacil.ui.allusertypes.auth.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository


class SplashViewModelFactory(
    val preferenceProvider: PreferenceProvider,
    val appRepository: AppRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(
            preferenceProvider,
            appRepository
        ) as T
    }
}