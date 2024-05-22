package fr.mbidon.lumeenproject.ui.joke.viewmodel

import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class JokeViewModelImplTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    private val mockedJokeRepository : JokeRepository = Mockito.mock(JokeRepository::class.java)
    private val viewModel = JokeViewModelImpl(mockedJokeRepository)

    @Test
    fun jokeViewModel_CorrectJokeRequest_JokeReceived() = runTest {
        // given
        assert(viewModel.getJokeState().value is JokeUIState.Empty)

        // when
        given(mockedJokeRepository.requestNewJoke()).willReturn(SingleJoke(jokeId = 1, joke = "Joke"))
        viewModel.onUserRequestsJoke()

        // then
        assert(viewModel.getJokeState().value == JokeUIState.Success(joke = SingleJoke(jokeId = 1, joke = "Joke")))
    }
}