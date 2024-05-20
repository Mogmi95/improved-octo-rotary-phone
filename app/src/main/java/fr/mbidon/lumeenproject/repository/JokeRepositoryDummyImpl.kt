package fr.mbidon.lumeenproject.repository

import android.util.Log
import fr.mbidon.lumeenproject.model.Joke
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.network.JokeDataSourceLocal
import fr.mbidon.lumeenproject.network.JokeDataSourceRemote

/**
 * Dummy implementation of the JokeRepository.
 * This implementation is used for testing purposes.
 */
class JokeRepositoryDummyImpl() : JokeRepository {
    override fun requestNewJoke(): Joke? {
        Log.d("JokeRepositoryDummyImpl", "requestNewJoke")
        return SingleJoke(1, "Hello world of jokes")
    }

    override fun getJokes(): List<Joke> {
        Log.d("JokeRepositoryDummyImpl", "getJokes")
        return(listOf(
            SingleJoke(1, "Hello world of jokes"),
            SingleJoke(2, "Hello world of jokes 2")
        ))
    }

    override fun clear() {
        Log.d("JokeRepositoryDummyImpl", "clear")
    }
}