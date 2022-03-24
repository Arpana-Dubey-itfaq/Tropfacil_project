package com.tropfacil.network.service

/**
 * Created by Nimesh Patel on 24-03-2022.
 */
sealed class SafeApiResponse<out T : Any> {
    class Success<out T : Any>(val data: T) : SafeApiResponse<T>()
    class Error(val exception: Throwable) : SafeApiResponse<Nothing>()
    object Loading : SafeApiResponse<Nothing>()
}
