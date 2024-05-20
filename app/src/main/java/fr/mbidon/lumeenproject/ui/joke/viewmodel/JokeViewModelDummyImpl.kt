package fr.mbidon.lumeenproject.ui.joke.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JokeViewModelDummyImpl @Inject constructor() : JokeViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(JokeUIState(joke = null))
    private val uiState: StateFlow<JokeUIState> = _uiState.asStateFlow()

    init {
        _uiState.value = JokeUIState(joke = null)
    }
    override fun getState(): StateFlow<JokeUIState> {
        return uiState
    }

    override fun onUserRequestsJoke() {
        _uiState.value = JokeUIState(
            joke = SingleJoke(42, "Hello world of jokes")
        )
    }

    override fun onUserRequestedJokeAsStarred(joke: Joke) {
        _uiState.value = JokeUIState(
            joke = SingleJoke(42, "Hello world of jokes"),
            isJokeStarred = true,
        )
    }
}