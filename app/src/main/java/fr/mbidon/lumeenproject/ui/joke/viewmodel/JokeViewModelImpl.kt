package fr.mbidon.lumeenproject.ui.joke.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModelImpl @Inject constructor(
    private val jokeRepository : JokeRepository
) : JokeViewModel, ViewModel() {

    // Joke status
    private val _uiJokeState = MutableStateFlow<JokeUIState>(JokeUIState.Empty)
    private val uiJokeState: StateFlow<JokeUIState> = _uiJokeState.asStateFlow()

    // Starred status for current joke
    private val _uiJokeStarredState = MutableStateFlow<JokeStarredUIState>(JokeStarredUIState.Empty)
    private val uiJokeStarredState: StateFlow<JokeStarredUIState> = _uiJokeStarredState.asStateFlow()

    private var monitoringJob : Job? = null

    override fun getJokeState(): StateFlow<JokeUIState> {
        return uiJokeState
    }

    override fun getJokeStarredState(): StateFlow<JokeStarredUIState> {
        return uiJokeStarredState
    }

    override fun onAttached() {
        // Monitoring the starred status of the current joke
        monitoringJob = viewModelScope.launch(Dispatchers.IO) {
            jokeRepository.getStarredJokes()
                .collect { jokes ->
                    val currentJoke = jokes.firstOrNull {
                        when (val state = _uiJokeState.value) {
                            is JokeUIState.Success -> it.id == state.joke.id
                            else -> false
                        }
                    }
                    delay(500)
                    if (currentJoke != null) {
                        _uiJokeStarredState.value = JokeStarredUIState.Success(isJokeStarred = true)
                    } else {
                        _uiJokeStarredState.value = JokeStarredUIState.Success(isJokeStarred = false)
                    }
                }
        }
    }

    override fun onDetached() {
        // Unregistering the monitoring of the starred status of the current joke
        monitoringJob?.cancel()
    }

    override fun onUserRequestsJoke() {
        _uiJokeState.value = JokeUIState.Loading
        _uiJokeStarredState.value = JokeStarredUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val newJoke = jokeRepository.requestNewJoke()
            // For user experience, we add a delay to show the loading state
            delay(500)
            if (newJoke == null) {
                _uiJokeState.value = JokeUIState.Error("Failed to get a new joke")
                _uiJokeStarredState.value = JokeStarredUIState.Empty
            } else {
                _uiJokeState.value = JokeUIState.Success(joke = newJoke)
                _uiJokeStarredState.value = JokeStarredUIState.Success(isJokeStarred = false)
            }
        }
    }
    override fun onUserRequestedJokeAsStarred(joke: Joke) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val state = _uiJokeStarredState.value) {
                is JokeStarredUIState.Success -> {
                    _uiJokeStarredState.value = JokeStarredUIState.Loading
                    if (state.isJokeStarred) {
                        jokeRepository.requestUnstarJoke(joke.id)
                    } else {
                        jokeRepository.requestStarJoke(joke)
                    }
                }
                else -> { /* Nothing */ }
            }
        }
    }
}