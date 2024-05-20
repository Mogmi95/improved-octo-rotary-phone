package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.network.JokeDataSourceLocal
import fr.mbidon.lumeenproject.network.JokeDataSourceRemote
import kotlinx.coroutines.flow.Flow

class JokeRepositoryImpl(
    private val jokeDataSourceRemote: JokeDataSourceRemote,
    private val jokeDataSourceLocal: JokeDataSourceLocal
) : JokeRepository {
    override fun requestNewJoke(): Joke? {
        TODO("Not yet implemented")
    }

    override fun getStarredJokes(): Flow<List<Joke>> {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}