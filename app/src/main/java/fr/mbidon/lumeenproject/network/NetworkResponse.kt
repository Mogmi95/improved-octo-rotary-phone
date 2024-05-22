package fr.mbidon.lumeenproject.network

/**
 * A *very* simple error wrapper.
 */
sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T?) : NetworkResponse<T>()
    data class Error(val message: String) : NetworkResponse<Nothing>()
}

