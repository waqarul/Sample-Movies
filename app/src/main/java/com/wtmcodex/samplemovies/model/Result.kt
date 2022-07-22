package com.wtmcodex.samplemovies.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: RemoteException) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }

    inline fun handle(onSuccess: (R) -> Unit = {}, onError: (Exception) -> Unit = {}) {
        when (this) {
            is Success -> onSuccess(this.data)
            is Error -> onError(this.exception)
        }
    }
}
