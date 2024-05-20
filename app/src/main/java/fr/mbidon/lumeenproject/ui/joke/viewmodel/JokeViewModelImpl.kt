package fr.mbidon.lumeenproject.ui.joke.viewmodel

import androidx.lifecycle.ViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.repository.JokeRepository
import fr.mbidon.lumeenproject.repository.JokeRepositoryDummyImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class JokeViewModelImpl : JokeViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(JokeUIState(joke = null))
    private val uiState: StateFlow<JokeUIState> = _uiState.asStateFlow()

    private val jokeRepository : JokeRepository = JokeRepositoryDummyImpl() // Todo handle injection

    override fun getState(): StateFlow<JokeUIState> {
        return uiState
    }

    override fun onUserRequestsJoke() {
        val newJoke = jokeRepository.requestNewJoke()
        _uiState.value = JokeUIState(joke = newJoke)
        // TODO handle error
    }
    override fun onUserRequestedJokeAsStarred(joke: Joke) {
        TODO("Not yet implemented")
    }
}