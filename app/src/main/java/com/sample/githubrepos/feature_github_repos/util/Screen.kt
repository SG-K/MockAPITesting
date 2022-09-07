package com.sample.githubrepos.feature_github_repos.util

sealed class Screen(val route : String) {

    object ReposScreen: Screen("repos_screen")

}