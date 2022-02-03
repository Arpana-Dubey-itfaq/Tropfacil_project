package com.tropfacil

import android.app.Application
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.data.repository.AppRepository
import com.tropfacil.data.repository.AppRepositoryImpl

import io.paperdb.Paper
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
       // bind() from singleton { networkModule.apiClientHelper(instance(), instance(), instance()) }

        // Repositories
        bind<AppRepository>() with singleton {
            AppRepositoryImpl(instance())
        }

        // ViewModel Factories




    }




    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}

