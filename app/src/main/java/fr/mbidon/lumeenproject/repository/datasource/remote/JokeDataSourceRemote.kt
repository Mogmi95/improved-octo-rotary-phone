package fr.mbidon.lumeenproject.repository.datasource.remote

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.network.NetworkResponse

interface JokeDataSourceRemote {

    /**
     * Requests new joke from the API.
     *
     * @return The new joke, or null if the request failed.
     */
    suspend fun requestNewJoke(): NetworkResponse<Joke>
}