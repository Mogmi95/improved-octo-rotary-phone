package fr.mbidon.lumeenproject.repository.datasource

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.StateFlow

class JokeDataSourceLocalRoomImpl: JokeDataSourceLocal {

    override fun getStarredJokes(): StateFlow<List<Joke>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveStarredJokes(jokes: List<Joke>) {
        TODO("Not yet implemented")
    }

    override suspend fun removeStarredJokesById(jokes: List<Int>) {
        TODO("Not yet implemented")
    }
}