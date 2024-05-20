package fr.mbidon.lumeenproject.ui.joke.viewmodel

import androidx.lifecycle.ViewModel
import fr.mbidon.lumeenproject.model.SingleJoke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class JokeViewModelImpl : JokeViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(JokeUIState(joke = null))
    private val uiState: StateFlow<JokeUIState> = _uiState.asStateFlow()

    override fun getState(): StateFlow<JokeUIState> {
        return uiState
    }

    override fun onUserRequestsJoke() {
        _uiState.value = JokeUIState(joke = SingleJoke(42, "Hello world of jokes"))
    }
}