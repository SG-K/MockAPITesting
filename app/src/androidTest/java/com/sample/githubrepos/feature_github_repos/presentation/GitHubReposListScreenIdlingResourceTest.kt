package com.sample.githubrepos.feature_github_repos.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.githubrepos.core.ideling_resources.ComposeCoutingIdleingResource
import com.sample.githubrepos.core.ideling_resources.ComposeOkHttp3IdlingResource
import com.sample.githubrepos.core.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GitHubReposListScreenIdlingResourceTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Inject
    lateinit var composeCoutingIdleingResource: ComposeCoutingIdleingResource
    @Inject
    lateinit var composeOkHttp3IdlingResource: ComposeOkHttp3IdlingResource


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    // Integration test to test API calls with composeCoutingIdleingResource intenrally using espresso idleresource regerting
    @Test
    fun getListIdlingResource_checkAndroidServiceRepo(){
        composeRule.onNodeWithTag(TestTags.Loading).assertExists()
        composeRule.onNodeWithTag(TestTags.Loading).assertIsDisplayed()
        composeRule.registerIdlingResource(composeCoutingIdleingResource)
        composeRule.onNodeWithText("Android-Service").assertExists()
        composeRule.onNodeWithText("Android-Service").assertIsDisplayed()
    }

    // Integration test to test API calls with composeOkHttp3IdlingResource intenrally using espresso idleresource regerting
    @Test
    fun getListIdlingResourceOkHttp_checkAndroidServiceRepo(){
        composeRule.onNodeWithTag(TestTags.Loading).assertExists()
        composeRule.onNodeWithTag(TestTags.Loading).assertIsDisplayed()
        composeRule.registerIdlingResource(composeOkHttp3IdlingResource)
        composeRule.onNodeWithText("Android-Service").assertExists()
        composeRule.onNodeWithText("Android-Service").assertIsDisplayed()
    }

    @After
    fun tearDown() {
        composeRule.unregisterIdlingResource(composeCoutingIdleingResource)
        composeRule.unregisterIdlingResource(composeOkHttp3IdlingResource)
    }

}