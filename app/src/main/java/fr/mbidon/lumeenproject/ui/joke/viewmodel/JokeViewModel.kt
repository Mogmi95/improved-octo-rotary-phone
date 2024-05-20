package fr.mbidon.lumeenproject.ui.joke.viewmodel

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.StateFlow

data class JokeUIState(
    val joke: Joke?
)
interface JokeViewModel {
    fun getState(): StateFlow<JokeUIState>
    fun onUserRequestsJoke()
}