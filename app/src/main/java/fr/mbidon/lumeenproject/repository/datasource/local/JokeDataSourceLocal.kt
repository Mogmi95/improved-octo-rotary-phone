package fr.mbidon.lumeenproject.repository.datasource.local

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeDataSourceLocal {

    /**
     * Recovers starred jokes from the local storage.
     */
    fun getStarredJokes(): Flow<List<Joke>>

    /**
     * Save starred jokes in the local storage.
     */
    suspend fun saveStarredJokes(jokes: List<Joke>)

    /**
     * Remove starred jokes from the local storage using their ID.
     */
    suspend fun removeStarredJokesById(jokes: List<Int>)
}