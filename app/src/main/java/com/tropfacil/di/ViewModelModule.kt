package com.tropfacil.di

import com.tropfacil.home.view.HomeViewModel
import com.tropfacil.ui.allusertypes.auth.forgotpass.ForgotPasswordViewModel
import com.tropfacil.ui.allusertypes.auth.login.LoginViewModel
import com.tropfacil.ui.allusertypes.auth.signup.RegisterViewModel
import com.tropfacil.ui.nav.account.AccountSettingsViewModel
import com.tropfacil.ui.nav.exercices.ExercicesViewModel
import com.tropfacil.ui.nav.profile.ProfileSettingsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module {
    /*viewModel { ChangePasswordViewModel(get(), get()) }
    viewModel { ProfileSettingsViewModel(get(), get()) }
    viewModel { VerificationCodeViewModel(get(), get()) }*/
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { ForgotPasswordViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AccountSettingsViewModel(get(), get()) }
    viewModel { ProfileSettingsViewModel(get(), get()) }
    viewModel { ExercicesViewModel(get(), get()) }
  /*  viewModel { AddCardViewModel(get(), get()) }
    viewModel { TokenRefresherViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { RestaurantProfileViewModel(get(), get()) }
    viewModel { ProductInfoViewModel(get(), get()) }
    viewModel { CartViewModel(get(), get()) }
    viewModel { OrderDetailsViewModel(get(), get()) }
    viewModel { OrderViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { ResetPasswordViewModel(get(), get()) }*/

}