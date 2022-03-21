package com.tropfacil.di




import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.data.repository.AppRepositoryImpl
import com.tropfacil.network.connectivity.ConnectivityInterceptor
import com.tropfacil.network.connectivity.ConnectivityInterceptorImpl
import com.tropfacil.network.service.networkModule
import org.koin.dsl.module.module

val managerModule = module {
    single { PreferenceProvider(get()) }
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get()) }
    single { AppRepository(get()) }
    single { AppRepositoryImpl(get()) }
    single { networkModule.getApiClientHelper(get(), get()) }
}