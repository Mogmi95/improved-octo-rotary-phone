package fr.mbidon.lumeenproject.ui.joke

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import fr.mbidon.lumeenproject.ui.joke.viewmodel.JokeViewModelImpl
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import fr.mbidon.lumeenproject.R
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

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(getString(R.string.activity_jokes_title))
                        },
                        actions = {
                            IconButton(onClick = {
                                // TODO Navigation logic should be handled elsewhere
                                val starredIntent = Intent(this@JokeActivity, StarredJokeActivity::class.java)
                                startActivity(starredIntent)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Access favorite jokes"
                                )
                            }
                        },

                        )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { jokeViewModel.onUserRequestsJoke() },
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Add")
                    }
                },

            ) { padding ->
                JokeScreen(
                    modifier = Modifier.padding(padding),
                    jokeViewModel = jokeViewModel,
                    onStarredListActivityRequested = {

                    },
                )
            }

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (jokeUIState.joke == null) {
                Text(stringResource(R.string.press_refresh))
            } else {
                ElevatedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        IconButton(onClick = { jokeViewModel.onUserRequestedJokeAsStarred(jokeUIState.joke!!) }) {
                            when (jokeUIState.isJokeStarred) {
                                true -> Icon(Icons.Default.Favorite, contentDescription = "Unstar")
                                false -> Icon(Icons.Default.FavoriteBorder, contentDescription = "Star")
                            }
                        }
                    }
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
            }
        }
    }
}