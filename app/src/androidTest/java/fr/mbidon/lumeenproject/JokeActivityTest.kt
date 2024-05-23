package fr.mbidon.lumeenproject

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import fr.mbidon.lumeenproject.ui.screen_joke.JokeActivity
import org.junit.Rule
import org.junit.Test

class JokeActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<JokeActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun simpleRequestJokeWorking() {
        composeTestRule.onNode(
            hasTestTag("screen_joke_empty_text")
        ).assertIsDisplayed()

        composeTestRule.onNode(
            hasTestTag("screen_joke_fab")
        ).performClick()

        composeTestRule.onNode(
            hasTestTag("screen_joke_loading")
        ).assertIsDisplayed()

        composeTestRule.waitUntilDoesNotExist(
            hasTestTag("screen_joke_loading"),
            timeoutMillis = 3000
        )

        composeTestRule.onNode(
            hasTestTag("screen_joke_card_container")
        ).assertIsDisplayed()
    }
}