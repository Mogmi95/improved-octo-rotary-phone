package fr.mbidon.lumeenproject.network.retrofit

import fr.mbidon.lumeenproject.network.NetworkResponse
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.network.api.RemoteJokeModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implementation of a client for the JokeApi using Retrofit.
 */
class RetrofitJokeApiClient: JokeApi {

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val jokeApi: RetrofitJokeApi by lazy {
        retrofit.create(RetrofitJokeApi::class.java)
    }

    override suspend fun getJoke(): NetworkResponse<RemoteJokeModel> {
        jokeApi.getJoke().let {
            return if (it.isSuccessful) {
                NetworkResponse(
                    hasError = false,
                    data = it.body()!!
                )
            } else {
                NetworkResponse(
                    hasError = true,
                    data = null
                )
            }
        }
    }
}