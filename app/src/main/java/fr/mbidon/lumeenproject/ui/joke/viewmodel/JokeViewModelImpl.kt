package fr.mbidon.lumeenproject.ui.joke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.JokeRepository
import fr.mbidon.lumeenproject.repository.JokeRepositoryDummyImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModelImpl : JokeViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(JokeUIState(joke = null))
    private val uiState: StateFlow<JokeUIState> = _uiState.asStateFlow()

    private val jokeRepository : JokeRepository = JokeRepositoryDummyImpl() // Todo handle injection

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