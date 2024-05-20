package fr.mbidon.lumeenproject.model

open class Joke(
    val id: Int,
    val type: JokeType,
)

enum class JokeType {
    SINGLE,
    TWO_STEPS
}