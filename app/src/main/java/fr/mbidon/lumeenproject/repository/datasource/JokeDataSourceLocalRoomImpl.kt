package fr.mbidon.lumeenproject.repository.datasource

import fr.mbidon.lumeenproject.model.Joke

class JokeDataSourceLocalRoomImpl: JokeDataSourceLocal {

    override suspend fun getStarredJokes(): List<Joke> {
        TODO("Not yet implemented")
    }

    override suspend fun saveStarredJokes(jokes: List<Joke>) {
        TODO("Not yet implemented")
    }
}