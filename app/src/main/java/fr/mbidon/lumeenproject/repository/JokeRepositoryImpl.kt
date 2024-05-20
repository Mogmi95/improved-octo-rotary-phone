package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.datasource.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.datasource.JokeDataSourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Singleton
class JokeRepositoryImpl(
    private val jokeDataSourceRemote: JokeDataSourceRemote,
    private val jokeDataSourceLocal: JokeDataSourceLocal
) : JokeRepository {

    override suspend fun requestNewJoke(): Joke? {
        return jokeDataSourceRemote.requestNewJoke()
    }

    override fun getStarredJokes(): StateFlow<List<Joke>> {
        TODO("Not yet implemented")
    }

    override suspend fun requestStarJoke(joke: Joke) {
        TODO("Not yet implemented")
    }

    override suspend fun requestUnstarJoke(jokeId: Int) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}