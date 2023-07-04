package com.sample.githubrepos.feature_github_repos.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.githubrepos.core.ideling_resources.ComposeCoutingIdleingResource
import com.sample.githubrepos.core.ideling_resources.ComposeOkHttp3IdlingResource
import com.sample.githubrepos.core.util.network.AlwaysOnlineNetworkMonitor
import com.sample.githubrepos.core.utils.TestTags
import com.sample.githubrepos.core.utils.network.ConnectivityManagerNetworkMonitor
import com.sample.githubrepos.core.utils.network.NetworkMonitor
import com.sample.githubrepos.di.BaseUrlModule
import com.sample.githubrepos.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class)
class GitHubReposListScreenIdlingResourceNoInternetTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Inject
    lateinit var composeCoutingIdleingResource: ComposeCoutingIdleingResource
    @Inject
    lateinit var composeOkHttp3IdlingResource: ComposeOkHttp3IdlingResource
    @Inject
    lateinit var monitor: NetworkMonitor

    @Before
    fun setUp() {
        hiltRule.inject()
        monitor.setConnected(true)
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

    @Test
    fun getListIdlingResourceOkHttp_checkAndroidServiceRepoNoNetwork(){
        print("flow_value_test entereted test")

//        composeRule.waitUntil(
//            condition = {
//                return@waitUntil composeRule
//                    .onAllNodesWithText("⚠️ Network Issue")
//                    .fetchSemanticsNodes().isNotEmpty()
//            }
//        )


        runBlocking {
            monitor.setConnected(true)
            delay(7_000)
            composeRule.onNodeWithText("⚠️ Network Issue").assertExists()
        }

//        composeRule.onNodeWithText("⚠️ Network Issue").assertIsDisplayed()

//        composeRule.onNodeWithTag(TestTags.Loading).assertExists()
//        composeRule.onNodeWithTag(TestTags.Loading).assertIsDisplayed()
//        composeRule.registerIdlingResource(composeOkHttp3IdlingResource)
//        composeRule.onNodeWithText("Android-Service").assertExists()
//        composeRule.onNodeWithText("Android-Service").assertIsDisplayed()
    }

    @After
    fun tearDown() {
        composeRule.unregisterIdlingResource(composeCoutingIdleingResource)
        composeRule.unregisterIdlingResource(composeOkHttp3IdlingResource)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface NetworkModuleTest {
        @Binds
        fun bindsNetworkMonitor(
            networkMonitor: AlwaysOnlineNetworkMonitor
        ): NetworkMonitor
    }

}