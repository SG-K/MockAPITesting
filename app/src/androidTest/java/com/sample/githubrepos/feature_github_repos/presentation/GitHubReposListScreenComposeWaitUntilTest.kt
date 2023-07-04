package com.sample.githubrepos.feature_github_repos.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.githubrepos.core.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GitHubReposListScreenComposeWaitUntilTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    // Integration test to test API calls with waitUntil function from compose testing library
    @Test
    fun getListWaitUnit_checkAndroidServiceRepo(){
        composeRule.onNodeWithTag(TestTags.Loading).assertExists()
        composeRule.onNodeWithTag(TestTags.Loading).assertIsDisplayed()

        composeRule.waitUntil(5000) {
            composeRule
                .onAllNodesWithTag(TestTags.Loading)
                .fetchSemanticsNodes().isEmpty()
        }

        composeRule.onNodeWithText("Android-Service").assertExists()
        composeRule.onNodeWithText("Android-Service").assertIsDisplayed()
    }

}