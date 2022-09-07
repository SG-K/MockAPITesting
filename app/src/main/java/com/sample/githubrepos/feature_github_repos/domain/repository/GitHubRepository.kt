package com.sample.githubrepos.feature_github_repos.domain.repository

import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData

interface GitHubRepository {

    suspend fun getUserRepos() : List<GitHubRepoData>

}