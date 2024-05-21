package fr.mbidon.lumeenproject.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fr.mbidon.lumeenproject.model.SingleJoke
import fr.mbidon.lumeenproject.model.TwoStepsJoke

@Preview
@Composable
fun SingleJokeComponentPreview() {
    SingleJokeComponent(
        singleJoke = SingleJoke(
            jokeId = 1,
            joke = "This is a single joke"
        )
    )
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

@Preview
@Composable
fun TwoStepsJokeComponentPreview() {
    TwoStepsJokeComponent(
        twoStepsJoke = TwoStepsJoke(
            jokeId = 1,
            setup = "This is a two steps joke setup",
            delivery = "This is a two steps joke delivery"
        )
    )
}
@Composable
fun TwoStepsJokeComponent(
    twoStepsJoke: TwoStepsJoke,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            color = Color.Red,
            text = twoStepsJoke.setup
        )
        Text(
            color = Color.Green,
            text = twoStepsJoke.delivery
        )
    }
}