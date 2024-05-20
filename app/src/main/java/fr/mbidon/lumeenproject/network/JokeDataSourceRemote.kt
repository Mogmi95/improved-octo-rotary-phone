package fr.mbidon.lumeenproject.network

import fr.mbidon.lumeenproject.model.Joke

interface JokeDataSourceRemote {

    /**
     * Requests new joke from the API.
     */
    suspend fun requestNewJoke(): Joke // TODO Wrap inside a Result
}