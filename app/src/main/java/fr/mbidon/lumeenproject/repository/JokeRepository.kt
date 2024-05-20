package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke

/**
 * Repository to manage Jokes
 */
interface JokeRepository {
    /**
     * Request a new joke.
     *
     * @return a new joke or null if the network is not available.
     */
    fun requestNewJoke(): Joke?

    /**
     * Get all jokes.
     *
     * @return the list of all available jokes.
     */
    fun getJokes(): List<Joke>

    /**
     * Deletes any local storage of jokes.
     */
    fun clear()
}