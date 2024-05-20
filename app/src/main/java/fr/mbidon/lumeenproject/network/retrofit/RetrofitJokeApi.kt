package fr.mbidon.lumeenproject.network.retrofit

import fr.mbidon.lumeenproject.network.api.RemoteJokeModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitJokeApi {

    /**
     * Requests a single joke from the API.
     */
    @GET("joke/Programming/Any?safe-mode")
    suspend fun getJoke(): Response<RemoteJokeModel>
}