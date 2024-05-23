package fr.mbidon.lumeenproject.network.api

import fr.mbidon.lumeenproject.network.NetworkResponse

interface JokeApi {

    /**
     * Requests a single joke from the API.
     *
     * @return A [NetworkResponse] wrapping the joke.
     */
    suspend fun getJoke(): NetworkResponse<RemoteJokeModel>
}