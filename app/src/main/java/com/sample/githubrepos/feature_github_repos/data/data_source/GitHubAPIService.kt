package com.sample.githubrepos.feature_github_repos.data.data_source

import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData
import retrofit2.http.GET

interface GitHubAPIService {

//    @GET("https://api.github.com/users/sg-k/repos")
    @GET("users/sg-k/repos")
    suspend fun getRepos() : List<GitHubRepoData>

}