package fr.mbidon.lumeenproject.network.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.network.retrofit.RetrofitJokeApiClient
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
final class NetworkModule {

    @Provides
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideJokeApi(okHttpClient: OkHttpClient) : JokeApi {
        return RetrofitJokeApiClient(okHttpClient)
    }

}