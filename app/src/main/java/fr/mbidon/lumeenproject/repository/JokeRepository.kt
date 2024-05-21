package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.StateFlow

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
     * Mark a joke as starred.
     *
     * If the joke is already starred, this method should do nothing.
     */
    suspend fun requestStarJoke(joke: Joke)

    /**
     * Remove a joke from the list of starred jokes.
     *
     * If the joke doesn't exist in the list of starred jokes, this method should do nothing.
     */
    suspend fun requestUnstarJoke(jokeId: Int)

    /**
     * Get all the starred jokes.
     *
     * @return a flow of available starred jokes.
     */
    fun getStarredJokes(): StateFlow<List<Joke>>
}