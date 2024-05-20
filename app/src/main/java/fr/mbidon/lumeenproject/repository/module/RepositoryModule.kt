package fr.mbidon.lumeenproject.repository.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.repository.JokeRepository
import fr.mbidon.lumeenproject.repository.JokeRepositoryImpl
import fr.mbidon.lumeenproject.repository.local.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.local.JokeDataSourceLocalImpl
import fr.mbidon.lumeenproject.repository.remote.JokeDataSourceRemote
import fr.mbidon.lumeenproject.repository.remote.JokeDataSourceRemoteImpl

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideJokeDataSourceRemote(jokeApi: JokeApi) : JokeDataSourceRemote {
        return JokeDataSourceRemoteImpl(jokeApi)
    }

    @Provides
    fun provideJokeDataSourceLocal() : JokeDataSourceLocal {
        return JokeDataSourceLocalImpl()
    }

    @Provides
    fun provideJokeRepository(
        jokeDataSourceRemote: JokeDataSourceRemote,
        jokeDataSourceLocal: JokeDataSourceLocal
    ): JokeRepository {
        return JokeRepositoryImpl(jokeDataSourceRemote, jokeDataSourceLocal)
    }

}