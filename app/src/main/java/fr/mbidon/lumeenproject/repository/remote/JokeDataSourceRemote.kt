package fr.mbidon.lumeenproject.repository.remote

import fr.mbidon.lumeenproject.model.Joke

interface JokeDataSourceRemote {

    /**
     * Requests new joke from the API.
     */
    suspend fun requestNewJoke(): Joke?
}