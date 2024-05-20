package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.Flow

/**
 * Repository to manage Jokes
 */
interface JokeRepository {
    /**
     * Request a new joke.
     *
     * @return a new joke or null if the network is not available.
     */
    suspend fun requestNewJoke(): Joke?

    /**
     * Get all the starred jokes.
     *
     * @return a flow of available starred jokes.
     */
    fun getStarredJokes(): Flow<List<Joke>>

    /**
     * Deletes any local storage of jokes.
     */
    fun clear()
}