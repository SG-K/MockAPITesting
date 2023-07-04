package com.sample.githubrepos.feature_github_repos.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.githubrepos.core.utils.TestTags
import com.sample.githubrepos.core.utils.network.NetworkMonitor
import com.sample.githubrepos.core.utils.print
import com.sample.githubrepos.feature_github_repos.presentation.components.GitHubReposListTopBar
import com.sample.githubrepos.feature_github_repos.presentation.components.TaskItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GitHubReposListScreen(
    navController: NavController,
    viewModel: GitHubReposViewModel = hiltViewModel()
){
    val state = viewModel.reposList.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var progress by remember { mutableStateOf(0.1f) }
    val networkState = viewModel.isOnlineViewmodel.collectAsState(initial = true)

    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Scaffold(
        topBar = {
            GitHubReposListTopBar()
        },
        scaffoldState = scaffoldState
    ) {

        AnimatedVisibility(
            visible = state.isLoading
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TestTags.Loading)
            ){
                CircularProgressIndicator()
            }
        }

        AnimatedVisibility(
            visible =( state.reposList.isEmpty() && !state.isLoading )
                    || !networkState.value
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = if (networkState.value){
                        "No Repository to show up"
                    } else { "⚠️ Network Issue" },
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
                )
                Text(
                    text = if (networkState.value){
                        "Add new repositories from GitHub site"
                    } else { " Please check your internet connection and try again" },
                    fontSize = 14.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                )

            }
        }

        AnimatedVisibility(visible = state.reposList.isNotEmpty() && networkState.value) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.reposList) { task ->
                    TaskItem(
                        githubRepoSata = task,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                "Task id in the click of the list - ${task.fullName}".print()
                            }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }




    }

}