package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.local.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.remote.JokeDataSourceRemote
import kotlinx.coroutines.flow.Flow

class JokeRepositoryImpl(
    private val jokeDataSourceRemote: JokeDataSourceRemote,
    private val jokeDataSourceLocal: JokeDataSourceLocal
) : JokeRepository {

    override suspend fun requestNewJoke(): Joke? {
        return jokeDataSourceRemote.requestNewJoke()
    }

    override fun getStarredJokes(): Flow<List<Joke>> {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}