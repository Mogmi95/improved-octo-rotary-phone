package fr.mbidon.lumeenproject.ui.starred.viewmodel

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
class StarredViewModelImpl @Inject constructor(
    private val jokeRepository : JokeRepository
) : StarredViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(StarredUIState(starredJokes = listOf()))
    private val uiState: StateFlow<StarredUIState> = _uiState.asStateFlow()

    private var monitoringJob : Job? = null
    init {
        _uiState.value = StarredUIState(starredJokes = emptyList())
    }

    override fun getState(): StateFlow<StarredUIState> {
        return uiState
    }

    override fun onAttached() {
        // Monitoring the starred status of the current joke
        monitoringJob = viewModelScope.launch(Dispatchers.IO) {
            jokeRepository.getStarredJokes()
                .collect { jokes ->
                    _uiState.value = StarredUIState(starredJokes = jokes)
                }
        }
    }

    override fun onDetached() {
        // Unregistering the monitoring of the starred status of the current joke
        monitoringJob?.cancel()
    }

    override fun onUserRequestedJokeUnstarred(joke: Joke) {
        TODO("Not yet implemented")
    }
}