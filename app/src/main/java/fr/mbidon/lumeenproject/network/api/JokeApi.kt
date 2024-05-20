package fr.mbidon.lumeenproject.network.api

import fr.mbidon.lumeenproject.network.NetworkResponse

interface JokeApi {

    /**
     * Requests a single joke from the API.
     */
    suspend fun getJoke(): NetworkResponse<RemoteJokeModel>
}