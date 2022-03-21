package com.app.leust.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository

import com.tropfacil.ui.allusertypes.auth.login.LoginViewModel

/**
 * Factory for ViewModels
 */
class ViewModelFactory(val preferenceProvider: PreferenceProvider, val authRepository: AppRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(preferenceProvider,authRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}