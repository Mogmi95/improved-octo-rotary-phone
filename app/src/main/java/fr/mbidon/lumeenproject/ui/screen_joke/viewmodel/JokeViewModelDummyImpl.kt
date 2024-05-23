package fr.mbidon.lumeenproject.ui.screen_joke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModelDummyImpl @Inject constructor() : JokeViewModel, ViewModel() {

    // Joke status
    private val _uiJokeState = MutableStateFlow<JokeUIState>(JokeUIState.Empty)
    private val uiJokeState: StateFlow<JokeUIState> = _uiJokeState.asStateFlow()

    // Starred status for current joke
    private val _uiJokeStarredState = MutableStateFlow<JokeStarredUIState>(JokeStarredUIState.Empty)
    private val uiJokeStarredState: StateFlow<JokeStarredUIState> = _uiJokeStarredState.asStateFlow()

    override fun onAttached() {
        // Nothing to do
    }

    override fun onDetached() {
        // Nothing to do
    }

    override fun getJokeState(): StateFlow<JokeUIState> {
        return uiJokeState
    }

    override fun getJokeStarredState(): StateFlow<JokeStarredUIState> {
        return uiJokeStarredState
    }

    override fun onUserRequestsJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiJokeState.value = JokeUIState.Loading
            _uiJokeStarredState.value = JokeStarredUIState.Loading

            delay(500)

            _uiJokeState.value = JokeUIState.Success(
                SingleJoke(
                    jokeId = 1,
                    joke = "Why did the scarecrow win an award? Because he was outstanding in his field!",
                )
            )
            _uiJokeStarredState.value = JokeStarredUIState.Success(isJokeStarred = false)
        }
    }

    override fun onUserRequestedJokeAsStarred(joke: Joke) {
        val state = _uiJokeStarredState.value
        if (state !is JokeStarredUIState.Success) {
            return
        }
        _uiJokeStarredState.value = JokeStarredUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            _uiJokeStarredState.value = JokeStarredUIState.Success(isJokeStarred = !state.isJokeStarred)
        }
    }
}