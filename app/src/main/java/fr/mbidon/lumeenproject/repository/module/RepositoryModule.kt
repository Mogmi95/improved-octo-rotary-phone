package fr.mbidon.lumeenproject.repository.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mbidon.lumeenproject.database.AppDatabase
import fr.mbidon.lumeenproject.network.api.JokeApi
import fr.mbidon.lumeenproject.repository.JokeRepository
import fr.mbidon.lumeenproject.repository.JokeRepositoryImpl
import fr.mbidon.lumeenproject.repository.datasource.local.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.datasource.local.JokeDataSourceLocalRoomImpl
import fr.mbidon.lumeenproject.repository.datasource.remote.JokeDataSourceRemote
import fr.mbidon.lumeenproject.repository.datasource.remote.JokeDataSourceRemoteImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideJokeDataSourceRemote(jokeApi: JokeApi) : JokeDataSourceRemote {
        return JokeDataSourceRemoteImpl(jokeApi)
    }

    @Provides
    @Singleton
    fun provideJokeDataSourceLocal(appDatabase: AppDatabase) : JokeDataSourceLocal {
        return JokeDataSourceLocalRoomImpl(appDatabase.jokeDao())
        // return JokeDataSourceLocalDummyImpl()
    }

    @Provides
    fun provideJokeRepository(
        jokeDataSourceRemote: JokeDataSourceRemote,
        jokeDataSourceLocal: JokeDataSourceLocal
    ): JokeRepository {
        return JokeRepositoryImpl(jokeDataSourceRemote, jokeDataSourceLocal)
        //return JokeRepositoryDummyImpl()
    }

}