package fr.mbidon.lumeenproject.repository.datasource.local

import fr.mbidon.lumeenproject.database.joke.JokeDao
import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class JokeDataSourceLocalRoomImpl(
    private val jokeDao: JokeDao,
): JokeDataSourceLocal {

    private val starredJokes = mutableListOf<Joke>()
    private val _starredJokesFlow = MutableStateFlow(emptyList<Joke>())

    override fun getStarredJokes(): Flow<List<Joke>> {
        return jokeDao.getAll().map { jokes ->
            jokes.map { jokeEntity ->
                jokeEntity.toJoke()
            }
        }
    }

    override suspend fun saveStarredJokes(jokes: List<Joke>) {
        jokes.map { joke ->
            jokeDao.insert(joke.toJokeEntity())
        }
    }

    override suspend fun removeStarredJokesById(jokes: List<Int>) {
        jokes.map { jokeId ->
            jokeDao.deleteWithId(jokeId)
        }
    }
}