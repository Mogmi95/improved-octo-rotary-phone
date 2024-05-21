package fr.mbidon.lumeenproject.repository

import android.util.Log
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton

/**
 * Dummy implementation of the JokeRepository.
 * This implementation is used for testing purposes.
 */
class JokeRepositoryDummyImpl : JokeRepository {

    private val starredJokes = mutableListOf<Joke>()
    private val _starredJokesFlow = MutableStateFlow(emptyList<Joke>())

    override suspend fun requestNewJoke(): Joke? {
        Log.d("JokeRepositoryDummyImpl", "requestNewJoke")
        val newJoke = SingleJoke(starredJokes.size + 1, "Hello world of ${starredJokes.size + 1}")
        return newJoke
    }

    override fun getStarredJokes() = _starredJokesFlow.asStateFlow()

    override suspend fun requestStarJoke(joke: Joke) {
        starredJokes.firstOrNull { it.id == joke.id }?.let {
            return
        }
        starredJokes.add(joke)
        notifyStarredJokesChanged()

    }

    override suspend fun requestUnstarJoke(jokeId: Int) {
        starredJokes.filter { it.id == jokeId }.forEach { starredJokes.remove(it) }
        notifyStarredJokesChanged()
    }

    private fun notifyStarredJokesChanged() {
        _starredJokesFlow.value = starredJokes.toList()
    }
}