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
    val showError = SingleLiveEvent<String>()
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
        val message = exception.message
        when (exception) {
            is HttpException -> {
                val errorJsonString = exception.response()!!.errorBody()!!.string()
                if (exception.code() != 500) {
                    try {
                        val errorResponse =
                            Gson().fromJson(errorJsonString, BaseResponse::class.java)
                      /*  if (errorResponse.validationMessages != null) {
                           // return errorResponse.validationMessages[0].message
                        }*/
                    } catch (ex: Exception) {
                        return "Something went wrong. Please try after sometime!"
                    }

                }
                return message
            }
            is NoConnectivityException -> return "No Internet Connection!"
            is SocketTimeoutException -> return "Connection with our server could not be established. Please try after sometime!"
            is Exception -> return "Something went wrong. Please try after sometime!"
            else -> return "Something went wrong. Please try after sometime!"
        }
    }

    /*fun isStatusSuccess(status: String): Boolean {
        return (status.equals("success",true) || status.equals("success!",true))
    }*/
}

