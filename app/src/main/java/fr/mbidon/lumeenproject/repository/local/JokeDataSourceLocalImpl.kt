package fr.mbidon.lumeenproject.repository.local

import fr.mbidon.lumeenproject.model.Joke

class JokeDataSourceLocalImpl: JokeDataSourceLocal {

    override suspend fun getStarredJokes(): List<Joke> {
        TODO("Not yet implemented")
    }

    override suspend fun saveStarredJokes(jokes: List<Joke>) {
        TODO("Not yet implemented")
    }
}