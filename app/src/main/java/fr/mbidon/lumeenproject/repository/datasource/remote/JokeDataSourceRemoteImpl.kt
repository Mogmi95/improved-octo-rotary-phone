package fr.mbidon.lumeenproject.repository.datasource.remote

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.network.toJoke

class JokeDataSourceRemoteImpl(
    private val jokeApi: JokeApi,
): JokeDataSourceRemote {
    override suspend fun requestNewJoke(): Joke? {
        val result = jokeApi.getJoke()
        if (result.hasError) {
            return null
        }
        return result.data!!.toJoke()
    }
}