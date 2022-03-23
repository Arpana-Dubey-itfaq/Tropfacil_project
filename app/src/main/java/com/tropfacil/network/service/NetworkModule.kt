package com.tropfacil.network.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tropfacil.BuildConfig

import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.network.connectivity.ConnectivityInterceptor
import com.tropfacil.network.service.ApiService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

/**
 * NetworkModule.
 */
const val PREF_TOKEN = "user_token"
const val CONNECT_TIMEOUT = 60L
const val WRITE_TIMEOUT = 30L
const val READ_TIMEOUT = 60L
var _preferenceProvider: PreferenceProvider? = null
const val PREF_IS_USER_LOGGED_IN = "user_logged_in"
object networkModule {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun getApiClientHelper(
        preferenceProvider: PreferenceProvider,
        connectivityInterceptor: ConnectivityInterceptor
    ): ApiService {
        _preferenceProvider = preferenceProvider

        val requestInterceptor = Interceptor { chain ->
            var request = chain.request()
            val builder = request.newBuilder()
            request = setAuthHeader(builder).build()
            return@Interceptor chain.proceed(request)
        }

        val sc = SSLContext.getInstance("TLSv1.2")
        sc.init(null, null, null)

        val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2).build()

        val specs = mutableListOf<ConnectionSpec>()
        specs.add(cs)
        specs.add(ConnectionSpec.COMPATIBLE_TLS)
        specs.add(ConnectionSpec.CLEARTEXT)

        val okHttpClient = OkHttpClient().newBuilder()
            .connectionSpecs(specs)
            .addInterceptor(requestInterceptor)
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()
            .create(ApiService::class.java)
    }


    private fun setAuthHeader(builder: Request.Builder): Request.Builder {
        builder.addHeader("accept", "application/json")
        /*if (_preferenceProvider!!.getBoolean(PREF_IS_USER_LOGGED_IN, false)) {*/
            builder.addHeader(
                "Access-Token",
                "v6yRZ5gsSPY0dS9imbUUySYuTdPGn5Wo"
            )
//        }
        return builder
    }
}