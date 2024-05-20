package fr.mbidon.lumeenproject.ui.joke.viewmodel

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.StateFlow

data class JokeUIState(
    val joke: Joke?,
    val isJokeStarred: Boolean = false,
)

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
     * Get the current state of the UI.
     */
    fun getState(): StateFlow<JokeUIState>

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