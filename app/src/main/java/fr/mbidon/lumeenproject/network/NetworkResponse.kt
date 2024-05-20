package fr.mbidon.lumeenproject.network

/**
 * A *very* simple error wrapper.
 */
data class NetworkResponse<T>(
    val data: T?,
    val hasError: Boolean,
)