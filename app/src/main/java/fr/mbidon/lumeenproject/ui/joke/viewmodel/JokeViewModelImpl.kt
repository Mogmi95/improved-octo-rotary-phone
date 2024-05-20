package fr.mbidon.lumeenproject.ui.joke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModelImpl @Inject constructor() : JokeViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(JokeUIState(joke = null))
    private val uiState: StateFlow<JokeUIState> = _uiState.asStateFlow()

    @Inject
    lateinit var jokeRepository : JokeRepository

    override fun getState(): StateFlow<JokeUIState> {
        return uiState
    }

    override fun onUserRequestsJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            val newJoke = jokeRepository.requestNewJoke()
            _uiState.value = JokeUIState(joke = newJoke)
        }
        // TODO UI Feedback
    }
    override fun onUserRequestedJokeAsStarred(joke: Joke) {
        TODO("Not yet implemented")
    }
}