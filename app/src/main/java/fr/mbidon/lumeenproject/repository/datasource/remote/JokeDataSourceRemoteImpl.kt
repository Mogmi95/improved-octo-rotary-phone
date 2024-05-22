package fr.mbidon.lumeenproject.repository.datasource.remote

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.network.NetworkResponse
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.network.toJoke

class JokeDataSourceRemoteImpl(
    private val jokeApi: JokeApi,
): JokeDataSourceRemote {
    override suspend fun requestNewJoke(): NetworkResponse<Joke> {

        return when (val result = jokeApi.getJoke()) {
            is NetworkResponse.Error -> NetworkResponse.Error("Network error")
            is NetworkResponse.Success -> NetworkResponse.Success(result.data!!.toJoke())
        }
    }
}