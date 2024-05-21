package fr.mbidon.lumeenproject.ui.joke

import android.content.Intent
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
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeViewModelImpl
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeViewModel
import fr.mbidon.lumeenproject.ui.shared.SingleJokeComponent
import fr.mbidon.lumeenproject.ui.shared.TwoStepsJokeComponent
import fr.mbidon.lumeenproject.ui.starred.StarredJokeActivity

@AndroidEntryPoint
class JokeActivity: AppCompatActivity(){

    // Type should not be hardcoded but depend on build flavor
    private val jokeViewModel: JokeViewModel by viewModels<JokeViewModelImpl>()

    override fun onResume() {
        super.onResume()
        jokeViewModel.onAttached()
    }

    override fun onPause() {
        super.onPause()
        jokeViewModel.onDetached()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JokeScreen(
                jokeViewModel = jokeViewModel,
                onStarredListActivityRequested = {
                    // TODO Navigation logic should be handled elsewhere
                    val starredIntent = Intent(this, StarredJokeActivity::class.java)
                    startActivity(starredIntent)
                },
            )
        }
    }

    @Composable
    fun JokeScreen(
        onStarredListActivityRequested: () -> Unit,
        jokeViewModel: JokeViewModel,
        modifier: Modifier = Modifier
    ) {
        val jokeUIState by jokeViewModel.getState().collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                // TODO .background(Color.Red)
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
                when (jokeUIState.isJokeStarred) {
                    true -> Text("Unstar this joke", modifier = Modifier.background(Color.Green))
                    false -> Text("Star this joke", modifier = Modifier.background(Color.Red))
                }
            }

            Button(
                onClick = { onStarredListActivityRequested() },
            ) {
                Text("See starred jokes")
            }
        }
    }
}