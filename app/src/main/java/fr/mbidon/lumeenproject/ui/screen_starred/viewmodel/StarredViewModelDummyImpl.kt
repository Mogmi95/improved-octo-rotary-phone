package fr.mbidon.lumeenproject.ui.screen_starred.viewmodel

import androidx.lifecycle.ViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StarredViewModelDummyImpl: StarredViewModel, ViewModel() {

    private val dummyStarredJokes = listOf(
        SingleJoke(1, "This is a single joke"),
        TwoStepsJoke(2, "This is a two steps joke", "This is the second step")
    )

    private val _uiState = MutableStateFlow(StarredUIState(starredJokes = emptyList()))
    private val uiState: StateFlow<StarredUIState> = _uiState.asStateFlow()

    init {
        _uiState.value = StarredUIState(starredJokes = dummyStarredJokes)
    }

    override fun onAttached() {
        // Nothing to do
    }

    override fun onDetached() {
        // Nothing to do
    }

    override fun getState(): StateFlow<StarredUIState> {
        return uiState
    }

    override fun onUserRequestedJokeUnstarred(joke: Joke) {
        TODO("Not yet implemented")
    }
}