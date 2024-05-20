package fr.mbidon.lumeenproject.network.api

data class RemoteJokeModel(
    val id: Int,
    val type: String,
    val setup: String? = null,
    val joke: String? = null,
    val delivery: String? = null,
)