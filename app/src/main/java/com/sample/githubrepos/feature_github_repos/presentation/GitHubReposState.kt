package com.sample.githubrepos.feature_github_repos.presentation

import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData

data class GitHubReposState(
    val reposList : List<GitHubRepoData>,
    val isLoading : Boolean
)
