package fr.mbidon.lumeenproject.ui.starred.viewmodel

import androidx.lifecycle.ViewModel
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeUIState
import kotlinx.coroutines.flow.StateFlow

class StarredViewModelDummyImpl: StarredViewModel, ViewModel() {

    override fun onAttached() {
        // Nothing to do
    }

    override fun onDetached() {
        // Nothing to do
    }

    override fun getState(): StateFlow<JokeUIState> {
        TODO("Not yet implemented")
    }

    override fun onUserRequestedJokeUnstarred(joke: Joke) {
        TODO("Not yet implemented")
    }
}