package com.sample.githubrepos.feature_github_repos.domain.use_case

import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData
import com.sample.githubrepos.feature_github_repos.domain.repository.GitHubRepository

class GetGitHubReposUseCase (
    private val repository: GitHubRepository
) {

    suspend operator fun invoke() : List<GitHubRepoData> {
        return repository.getUserRepos()
    }
}