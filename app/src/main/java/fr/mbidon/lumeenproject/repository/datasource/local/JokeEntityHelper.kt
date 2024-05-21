package fr.mbidon.lumeenproject.repository.datasource.local

import fr.mbidon.lumeenproject.database.joke.JokeEntity
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke

internal fun JokeEntity.toJoke() : Joke {
    when (this.type) {
        "single" -> {
            return SingleJoke(
                jokeId = this.id,
                joke = this.joke!!,
            )
        }
        "twopart" -> {
            return TwoStepsJoke(
                jokeId = this.id,
                setup = this.setup!!,
                delivery = this.delivery!!,
            )
        }
        else -> {
            throw IllegalArgumentException("Unknown joke type")
        }
    }
}

internal fun Joke.toJokeEntity() : JokeEntity {
    return when (this) {
        is SingleJoke -> {
            JokeEntity(
                id = this.jokeId,
                type = "single",
                setup = null,
                joke = this.joke,
                delivery = null,
            )
        }
        is TwoStepsJoke -> {
            JokeEntity(
                id = this.jokeId,
                type = "twopart",
                setup = this.setup,
                joke = null,
                delivery = this.delivery,
            )
        }
        else -> {
            throw IllegalArgumentException("Unknown joke type")
        }
    }
}
