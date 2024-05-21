package fr.mbidon.lumeenproject.repository.datasource

import android.util.Log
import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class JokeDataSourceLocalDummyImpl : JokeDataSourceLocal {

    private val starredJokes = mutableListOf<Joke>()
    private val _starredJokesFlow = MutableStateFlow(emptyList<Joke>())

    override fun getStarredJokes(): StateFlow<List<Joke>> {
        Log.d("JokeDataSourceLocalDummyImpl", "SUBSCRIBED TO STARRED JOKES FLOW ${this.hashCode()}")
        return _starredJokesFlow.asStateFlow()
    }

    override suspend fun saveStarredJokes(jokes: List<Joke>) {
        starredJokes += jokes
        notifyStarredJokesChanged()
    }

    override suspend fun removeStarredJokesById(jokes: List<Int>) {
        starredJokes.removeAll { joke ->
            jokes.contains(joke.id)
        }
        notifyStarredJokesChanged()
    }

    private fun notifyStarredJokesChanged() {
        Log.d("JokeDataSourceLocalDummyImpl", "notifyStarredJokesChanged ${starredJokes}")
        _starredJokesFlow.value = starredJokes.toList()
    }
}