package com.sample.githubrepos.feature_github_repos.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.githubrepos.core.util.MockServerDispatcher
import com.sample.githubrepos.di.BaseUrlModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Improvement for this test case is [[GitHubReposListScreenComposeWaitUntilTest]]
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class)
class GitHubReposListScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        hiltRule.inject()

        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
    }

    // Integration test to test API calls with MockWebServer
    @Test
    fun getListWaitUnit_checkAndroidServiceRepo(){
        composeRule.onNodeWithText("Android-Service").assertExists()
        composeRule.onNodeWithText("Android-Service").assertIsDisplayed()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestBaseUrlModule {

        @Provides
        fun provideBaseUrl() = "http://localhost:8080/"

    }

}