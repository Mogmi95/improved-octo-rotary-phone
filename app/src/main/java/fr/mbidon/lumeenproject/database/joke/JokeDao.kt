package fr.mbidon.lumeenproject.database.joke

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokeEntity")
    fun getAll(): Flow<List<JokeEntity>>

    @Insert
    fun insert(jokeEntitie: JokeEntity)

    @Query("DELETE FROM jokeEntity WHERE id = :jokeId")
    fun deleteWithId(jokeId: Int)
}