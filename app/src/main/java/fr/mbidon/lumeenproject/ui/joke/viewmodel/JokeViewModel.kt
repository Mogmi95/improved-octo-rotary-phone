package fr.mbidon.lumeenproject.ui.joke.viewmodel

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.StateFlow

sealed interface JokeUIState {
    data object Empty : JokeUIState
    data object Loading : JokeUIState
    data class Success(val joke: Joke) : JokeUIState
    data class Error(val message: String) : JokeUIState
}

sealed interface JokeStarredUIState {
    data object Empty : JokeStarredUIState
    data object Loading : JokeStarredUIState
    data class Success(val isJokeStarred: Boolean) : JokeStarredUIState
    data class Error(val message: String) : JokeStarredUIState
}

/**
 * ViewModel for the Joke screen.
 */
interface JokeViewModel {

    /**
     * Called when the View is attached to the screen.
     */
    fun onAttached()

    /**
     * Called when the View is detached from the screen.
     */
    fun onDetached()

    /**
     * Get info about the current joke.
     */
    fun getJokeState(): StateFlow<JokeUIState>

    /**
     * Get info about the current joke starred status.
     */
    fun getJokeStarredState(): StateFlow<JokeStarredUIState>

    /**
     * The user requested a new joke.
     *
     * This should trigger a network request to get a new joke, or display an error
     * message if the request fails.
     */
    fun onUserRequestsJoke()

    /**
     * The user requested a joke to be starred.
     *
     * The provided joke should be marked as starred in the local database and trigger an UI
     * update.
     */
    fun onUserRequestedJokeAsStarred(joke: Joke)
}