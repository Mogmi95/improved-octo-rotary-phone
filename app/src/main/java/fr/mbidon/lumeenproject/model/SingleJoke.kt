package fr.mbidon.lumeenproject.model

data class SingleJoke(
    var jokeId: Int,
    val joke: String
) : Joke(jokeId, JokeType.SINGLE)
