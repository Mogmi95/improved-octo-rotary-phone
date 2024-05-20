package fr.mbidon.lumeenproject.ui.joke

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeViewModelImpl
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeViewModelDummyImpl

class JokeActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JokeScreen(
                onStarredListActivityRequested = { /*TODO*/ }
            )
        }
    }


    @Composable
    fun JokeScreen(
        onStarredListActivityRequested: () -> Unit,
        jokeViewModel: JokeViewModelImpl = viewModel(), // TODO Handle injection
        modifier: Modifier = Modifier
    ) {
        val jokeUIState by jokeViewModel.getState().collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            if (jokeUIState.joke == null) {
                Text("---")
            } else {
                when (jokeUIState.joke) {
                    is SingleJoke -> {
                        SingleJokeComponent(
                            singleJoke = jokeUIState.joke as SingleJoke,
                            modifier = modifier
                        )
                    }
                    is TwoStepsJoke -> {
                        TwoStepsJokeComponent(
                            twoStepsJoke = jokeUIState.joke as TwoStepsJoke,
                            modifier = modifier
                        )
                    }
                }
            }

            Button(
                onClick = { jokeViewModel.onUserRequestsJoke() },
            ) {
                Text("Get a new joke")
            }

            Button(
                enabled = jokeUIState.joke != null,
                onClick = { jokeViewModel.onUserRequestedJokeAsStarred(jokeUIState.joke!!) },
            ) {
                Text("Star this joke")
            }

            Button(
                onClick = { onStarredListActivityRequested() },
            ) {
                Text("See starred jokes")
            }
        }
    }
    @Composable
    fun SingleJokeComponent(
        singleJoke: SingleJoke,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = singleJoke.joke
        )
    }

    @Composable
    fun TwoStepsJokeComponent(
        twoStepsJoke: TwoStepsJoke,
        modifier: Modifier = Modifier
    ) {
        Column {
            Text(
                text = twoStepsJoke.setup
            )
            Text(
                text = twoStepsJoke.delivery
            )
        }
    }
}