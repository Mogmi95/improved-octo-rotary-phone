package fr.mbidon.lumeenproject.database.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.mbidon.lumeenproject.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
final class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "JokeDatabase"
        ).build()
    }
}