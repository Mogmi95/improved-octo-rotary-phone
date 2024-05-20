package fr.mbidon.lumeenproject.model

data class TwoStepsJoke(
    var jokeId: Int,
    val setup: String,
    val delivery: String
) : Joke(jokeId, JokeType.TWO_STEPS)
