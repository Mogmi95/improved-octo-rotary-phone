package fr.mbidon.lumeenproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.mbidon.lumeenproject.database.joke.JokeDao
import fr.mbidon.lumeenproject.database.joke.JokeEntity

@Database(entities = [JokeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}
