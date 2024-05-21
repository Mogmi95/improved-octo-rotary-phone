package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.datasource.local.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.datasource.remote.JokeDataSourceRemote

class JokeRepositoryImpl(
    private val jokeDataSourceRemote: JokeDataSourceRemote,
    private val jokeDataSourceLocal: JokeDataSourceLocal
) : JokeRepository {

    override suspend fun requestNewJoke(): Joke? {
        return jokeDataSourceRemote.requestNewJoke()
    }

    override fun getStarredJokes()= jokeDataSourceLocal.getStarredJokes()

    override suspend fun requestStarJoke(joke: Joke) {
        jokeDataSourceLocal.saveStarredJokes(listOf(joke))
    }

    override suspend fun requestUnstarJoke(jokeId: Int) {
        jokeDataSourceLocal.removeStarredJokesById(listOf(jokeId))
    }
}