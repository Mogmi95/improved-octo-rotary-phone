package fr.mbidon.lumeenproject.network

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke
import fr.mbidon.lumeenproject.network.api.RemoteJokeModel

fun RemoteJokeModel.toJoke(): Joke {
    when (this.type){
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
            throw Exception("Unknown joke type")
        }
    }
}