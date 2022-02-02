package com.tropfacil.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tropfacil.network.BaseResponse
import com.tropfacil.utils.NoConnectivityException
import com.tropfacil.utils.SingleLiveEvent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.HttpException
import java.net.SocketTimeoutException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String?>()
    val unAuthorizedUser = SingleLiveEvent<String>()

    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

    fun getErrorMessage(exception: Throwable): String? {
        var message : String? = "nous rencontrons un problème. Merci de réessayer plus tard.!"
        when (exception) {
            is HttpException -> {
                val errorJsonString = exception.response()
                    .errorBody()?.string()
                if (exception.code() != 500) {
                    try {
                        val errorResponse =
                            Gson().fromJson(errorJsonString, BaseResponse::class.java)
                        message = if (errorResponse != null) {
                            if (errorResponse.message.isNullOrEmpty() && errorResponse.error_description.isNotEmpty()) {
                                errorResponse.error_description
                            } else {
                                errorResponse.message
                            }
                        } else
                            "nous rencontrons un problème. Merci de réessayer plus tard.!"

                        errorResponse.statusCode?.let {
                            if (it == 401) {
                                unAuthorizedUser.value = message!!
                                message = null
                            }
                        }
                    } catch (ex: Exception) {
                        message = "nous rencontrons un problème. Merci de réessayer plus tard.!"
                    }

                }

                return message
            }
            is NoConnectivityException -> return "Pas de connexion internet.!"
            is SocketTimeoutException -> return "La connexion avec le serveur n’a pas pu être établie.!"
            is Exception -> return "Merci de réessayer plus tard.!"
            else -> return "nous rencontrons un problème. Merci de réessayer plus tard.!"
        }
    }
/*

    fun getErrorMessage(exception: Throwable): String? {
        var message = exception.message
        when (exception) {
            is HttpException -> {
                val errorJsonString = exception.response()
                    .errorBody()?.string()
                if (exception.code() != 500) {
                    try {
                        val errorResponse =
                            Gson().fromJson(errorJsonString, BaseResponse::class.java)
                        message = if (errorResponse != null) {
                            if (errorResponse.message.isNullOrEmpty() && errorResponse.error_description.isNotEmpty()) {
                                errorResponse.error_description
                            } else {
                                errorResponse.message
                            }
                        } else
                            "nous rencontrons un problème. Merci de réessayer plus tard.!"

                        errorResponse.statusCode?.let {
                            if (it == 401) {
                                unAuthorizedUser.value = message
                                message = null
                            }
                        }
                    } catch (ex: Exception) {
                        message = "nous rencontrons un problème. Merci de réessayer plus tard.!"
                    }

                }

                return message
            }
            is NoConnectivityException -> return "Pas de connexion internet.!"
            is SocketTimeoutException -> return "La connexion avec le serveur n’a pas pu être établie.!"
            is Exception -> return "Merci de réessayer plus tard.!"
            else -> return "nous rencontrons un problème. Merci de réessayer plus tard.!"
        }
    }
*/
}

