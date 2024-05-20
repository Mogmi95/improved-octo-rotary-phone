package fr.mbidon.lumeenproject.repository.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.repository.JokeRepository
import fr.mbidon.lumeenproject.repository.JokeRepositoryDummyImpl
import fr.mbidon.lumeenproject.repository.datasource.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.datasource.JokeDataSourceLocalRoomImpl
import fr.mbidon.lumeenproject.repository.datasource.JokeDataSourceRemote
import fr.mbidon.lumeenproject.repository.datasource.JokeDataSourceRemoteImpl

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideJokeDataSourceRemote(jokeApi: JokeApi) : JokeDataSourceRemote {
        return JokeDataSourceRemoteImpl(jokeApi)
    }

    @Provides
    fun provideJokeDataSourceLocal() : JokeDataSourceLocal {
        return JokeDataSourceLocalRoomImpl()
    }

    @Provides
    fun provideJokeRepository(
        jokeDataSourceRemote: JokeDataSourceRemote,
        jokeDataSourceLocal: JokeDataSourceLocal
    ): JokeRepository {
        //return JokeRepositoryImpl(jokeDataSourceRemote, jokeDataSourceLocal)
        return JokeRepositoryDummyImpl()
    }

}