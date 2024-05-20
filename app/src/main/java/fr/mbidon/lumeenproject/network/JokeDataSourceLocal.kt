package fr.mbidon.lumeenproject.network

import fr.mbidon.lumeenproject.model.Joke

interface JokeDataSourceLocal {

    /**
     * Recovers starred jokes from the local storage.
     */
    suspend fun getStarredJokes(): List<Joke>

    /**
     * Save starred jokes in the local storage.
     */
    suspend fun saveStarredJokes(jokes: List<Joke>)
}