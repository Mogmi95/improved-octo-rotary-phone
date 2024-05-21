package fr.mbidon.lumeenproject.database.joke

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JokeEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "setup") val setup: String?,
    @ColumnInfo(name = "joke") val joke: String?,
    @ColumnInfo(name = "delivery") val delivery: String?,
)
