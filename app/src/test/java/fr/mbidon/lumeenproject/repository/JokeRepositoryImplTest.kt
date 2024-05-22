package fr.mbidon.lumeenproject.repository

import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.network.NetworkResponse
import fr.mbidon.lumeenproject.repository.datasource.local.JokeDataSourceLocal
import fr.mbidon.lumeenproject.repository.datasource.remote.JokeDataSourceRemote
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

class JokeRepositoryImplTest {


    private val mockedJokeDataSourceRemote: JokeDataSourceRemote = Mockito.mock(JokeDataSourceRemote::class.java)
    private val mockedJokeDataSourceLocal: JokeDataSourceLocal = Mockito.mock(JokeDataSourceLocal::class.java)

    // Tested
    private val jokeRepositoryImpl = JokeRepositoryImpl(mockedJokeDataSourceRemote, mockedJokeDataSourceLocal)

    @Test
    fun jokeRepositoryImpl_CorrectJokeRequest_JokeReceived() = runTest {
        // Given
        val fakeJoke = SingleJoke(jokeId = 1, joke = "Joke")
        given(mockedJokeDataSourceRemote.requestNewJoke()).willReturn(NetworkResponse.Success(fakeJoke))

        // When
        val result = jokeRepositoryImpl.requestNewJoke()

        // Then
        assert(result == fakeJoke)
    }

    @Test
    fun jokeRepositoryImpl_NoInternetJokeRequest_NoJokeReceived() = runTest {
        // Given
        given(mockedJokeDataSourceRemote.requestNewJoke()).willReturn(NetworkResponse.Error("Network error"))

        // When
        val result = jokeRepositoryImpl.requestNewJoke()

        // Then
        assert(result == null)
    }

}