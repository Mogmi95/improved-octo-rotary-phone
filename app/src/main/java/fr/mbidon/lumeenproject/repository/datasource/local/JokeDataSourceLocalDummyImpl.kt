package fr.mbidon.lumeenproject.repository.datasource.local

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class JokeDataSourceLocalDummyImpl : JokeDataSourceLocal {

    private val starredJokes = mutableListOf<Joke>()
    private val _starredJokesFlow = MutableStateFlow(emptyList<Joke>())

    override fun getStarredJokes(): StateFlow<List<Joke>> {
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
        _starredJokesFlow.value = starredJokes.toList()
    }
}