package com.sample.githubrepos.feature_github_repos.data.repository

import com.sample.githubrepos.feature_github_repos.data.data_source.GitHubAPIService
import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData
import com.sample.githubrepos.feature_github_repos.domain.repository.GitHubRepository

class GitHubRepositoryImpl(
    private val gitHubAPIService: GitHubAPIService
) : GitHubRepository {

    override suspend fun getUserRepos(): List<GitHubRepoData> {
        return gitHubAPIService.getRepos()
    }

}