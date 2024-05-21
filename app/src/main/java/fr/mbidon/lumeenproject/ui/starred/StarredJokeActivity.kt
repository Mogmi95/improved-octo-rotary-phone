package fr.mbidon.lumeenproject.ui.starred

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import fr.mbidon.lumeenproject.ui.starred.viewmodel.StarredViewModel
import fr.mbidon.lumeenproject.ui.starred.viewmodel.StarredViewModelDummyImpl

@AndroidEntryPoint
class StarredJokeActivity : AppCompatActivity() {

    // Type should not be hardcoded but depend on build flavor
    private val starredViewModel: StarredViewModel by viewModels<StarredViewModelDummyImpl>()

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

        setContent {
            StarredScreen(
                starredViewModel = starredViewModel
            )
        }
    }

    @Composable
    fun StarredScreen(
        starredViewModel: StarredViewModel,
        modifier: Modifier = Modifier
    ) {
        val starredUIState by starredViewModel.getState().collectAsState()

        Text("Starred jokes")
    }
}