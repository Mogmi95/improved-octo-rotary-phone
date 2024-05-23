package fr.mbidon.lumeenproject.ui.starred

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import fr.mbidon.lumeenproject.R
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke
import fr.mbidon.lumeenproject.ui.shared.SingleJokeComponent
import fr.mbidon.lumeenproject.ui.shared.TwoStepsJokeComponent
import fr.mbidon.lumeenproject.ui.starred.viewmodel.StarredViewModel
import fr.mbidon.lumeenproject.ui.starred.viewmodel.StarredViewModelImpl
import fr.mbidon.lumeenproject.ui.theme.AppTheme

@AndroidEntryPoint
class StarredJokeActivity : AppCompatActivity() {

    // Type should not be hardcoded but depend on build flavor
    private val starredViewModel: StarredViewModel by viewModels<StarredViewModelImpl>()

    override fun onResume() {
        super.onResume()
        starredViewModel.onAttached()
    }

    override fun onPause() {
        super.onPause()
        starredViewModel.onDetached()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(getString(R.string.activity_starred_title))
        actionBar?.displayOptions = android.app.ActionBar.DISPLAY_HOME_AS_UP
        setContent {
            AppTheme {
                StarredScreen(
                    starredViewModel = starredViewModel
                )
            }
        }
    }

    @Composable
    fun StarredScreen(
        starredViewModel: StarredViewModel,
        modifier: Modifier = Modifier
    ) {
        val starredUIState by starredViewModel.getState().collectAsState()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(starredUIState.starredJokes) { starredJoke ->
                ElevatedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        IconButton(onClick = { starredViewModel.onUserRequestedJokeUnstarred(starredJoke) }) {
                            // The joke will disappear from the list once unstarred
                            // TODO Progress status
                            Icon(Icons.Default.Favorite, contentDescription = "Unstar")
                        }
                    }
                    when (starredJoke) {
                        is SingleJoke -> {
                            SingleJokeComponent(
                                singleJoke = starredJoke,
                                modifier = modifier
                            )
                        }
                        is TwoStepsJoke -> {
                            TwoStepsJokeComponent(
                                twoStepsJoke = starredJoke,
                                modifier = modifier
                            )
                        }
                    }
                }

            }
        }
    }
}