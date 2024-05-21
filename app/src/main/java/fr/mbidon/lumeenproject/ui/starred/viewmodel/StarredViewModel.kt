package fr.mbidon.lumeenproject.ui.starred.viewmodel

import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeUIState
import kotlinx.coroutines.flow.StateFlow

data class StarredUIState(
    val starredJokes: List<JokeUIState>
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
    fun getState(): StateFlow<JokeUIState>

    /**
     * The user requested a joke to be starred.
     *
     * The provided joke should be unstarred and removed from the list.
     */
    fun onUserRequestedJokeUnstarred(joke: Joke)
}