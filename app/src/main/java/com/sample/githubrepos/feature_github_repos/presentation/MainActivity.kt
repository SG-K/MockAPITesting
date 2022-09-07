package com.sample.githubrepos.feature_github_repos.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.githubrepos.feature_github_repos.util.Screen
import com.sample.githubrepos.ui.theme.GitHubReposTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubReposTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ReposScreen.route
                    ) {
                        composable(route = Screen.ReposScreen.route) {
                            GitHubReposListScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    viewModel: GitHubReposViewModel = hiltViewModel()
) {
    val state = viewModel.reposList.value
    Text(text = "Hello repos size ${state.reposList.size}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitHubReposTheme {
        Greeting("Android")
    }
}