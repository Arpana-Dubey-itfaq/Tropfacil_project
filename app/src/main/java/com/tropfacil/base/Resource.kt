package com.app.leust.ui.base

open class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val apiErrorModel: ApiErrorResponseModel?
) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: String, data: T?): Resource<T> {
        return Resource(ResourceState.ERROR, null, apiErrorModel)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

    fun <T> initial(): Resource<T> {
        return Resource(ResourceState.INITIAL, null, null)
    }

}

enum class ResourceState {
    INITIAL, LOADING, SUCCESS, ERROR
}