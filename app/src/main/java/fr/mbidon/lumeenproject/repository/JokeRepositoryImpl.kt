package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.network.NetworkResponse
import fr.mbidon.lumeenproject.repository.datasource.local.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.datasource.remote.JokeDataSourceRemote

class JokeRepositoryImpl(
    private val jokeDataSourceRemote: JokeDataSourceRemote,
    private val jokeDataSourceLocal: JokeDataSourceLocal
) : JokeRepository {

    override suspend fun requestNewJoke(): Joke? {

        return when (val newJoke = jokeDataSourceRemote.requestNewJoke()) {
            is NetworkResponse.Error -> null // Could propagate another Status instead of null
            is NetworkResponse.Success -> newJoke.data
        }
    }

    override fun getStarredJokes()= jokeDataSourceLocal.getStarredJokes()

    override suspend fun requestStarJoke(joke: Joke) {
        jokeDataSourceLocal.saveStarredJokes(listOf(joke))
    }

    override suspend fun requestUnstarJoke(jokeId: Int) {
        jokeDataSourceLocal.removeStarredJokesById(listOf(jokeId))
    }
}