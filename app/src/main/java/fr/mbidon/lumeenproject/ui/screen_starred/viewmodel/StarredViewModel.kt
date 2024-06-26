package fr.mbidon.lumeenproject.ui.screen_starred.viewmodel

import fr.mbidon.lumeenproject.model.Joke
import kotlinx.coroutines.flow.StateFlow

data class StarredUIState(
    val starredJokes: List<Joke>
)

/**
 * ViewModel for the list of starred jokes.
 */
interface StarredViewModel {

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
    fun getState(): StateFlow<StarredUIState>

    /**
     * The user requested a joke to be starred.
     *
     * The provided joke should be unstarred and removed from the list.
     */
    fun onUserRequestedJokeUnstarred(joke: Joke)
}