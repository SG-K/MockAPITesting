package com.sample.githubrepos.feature_github_repos.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData

@Composable
fun TaskItem(
    githubRepoSata: GitHubRepoData,
    modifier: Modifier = Modifier
){

    Card(
        elevation = 5.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = githubRepoSata.fullName?.replace("SG-K/", "") ?: "No Title",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = !githubRepoSata.description.isNullOrEmpty()) {
                Text(
                    text = githubRepoSata.description?:"No Description",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }

}

