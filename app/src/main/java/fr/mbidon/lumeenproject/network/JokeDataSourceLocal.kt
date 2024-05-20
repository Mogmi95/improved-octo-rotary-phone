package fr.mbidon.lumeenproject.network

import fr.mbidon.lumeenproject.model.Joke

interface JokeDataSourceLocal {

    /**
     * Recovers jokes from the local storage.
     */
    suspend fun getCachedJokes(): List<Joke>

    /**
     * Saves jokes in the local storage.
     */
    suspend fun saveJokesToCache(jokes: List<Joke>)
}