package fr.mbidon.lumeenproject.ui.joke.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModelImpl @Inject constructor(
    private val jokeRepository : JokeRepository
) : JokeViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(JokeUIState(joke = null))
    private val uiState: StateFlow<JokeUIState> = _uiState.asStateFlow()

    private var monitoringJob : Job? = null

    override fun getState(): StateFlow<JokeUIState> {
        return uiState
    }

    override fun onAttached() {
        // Monitoring the starred status of the current joke
        monitoringJob = viewModelScope.launch(Dispatchers.IO) {
            jokeRepository.getStarredJokes()
                .collect { jokes ->
                    val currentJoke = jokes.firstOrNull { it.id == _uiState.value.joke?.id }
                    _uiState.value = _uiState.value.copy(isJokeStarred = currentJoke != null)
                }
        }
    }

    override fun onDetached() {
        // Unregistering the monitoring of the starred status of the current joke
        monitoringJob?.cancel()
    }

    override fun onUserRequestsJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            val newJoke = jokeRepository.requestNewJoke()
            _uiState.value = JokeUIState(joke = newJoke)
        }
        // TODO UI Feedback
    }
    override fun onUserRequestedJokeAsStarred(joke: Joke) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.isJokeStarred) {
                jokeRepository.requestUnstarJoke(joke.id)
            } else {
                jokeRepository.requestStarJoke(joke)
            }

        }
    }
}