package com.sample.githubrepos.feature_github_repos.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubrepos.core.utils.print
import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData
import com.sample.githubrepos.feature_github_repos.domain.use_case.GetGitHubReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GitHubReposViewModel @Inject constructor(
    private val gitHubReposUseCase: GetGitHubReposUseCase,
) : ViewModel() {

    private val _reposlist = mutableStateOf(GitHubReposState(emptyList(), true))
    val reposList: State<GitHubReposState> = _reposlist


    init {
        viewModelScope.launch {
            "tasks list initiating".print()
            _reposlist.value = reposList.value.copy(
                isLoading = true
            )
            try {
                gitHubReposUseCase.invoke().also { list ->
                    "tasks list size - ${list.size}".print()
                    _reposlist.value = reposList.value.copy(
                        reposList = list,
                        isLoading = false
                    )
                }
            }catch (e:Exception){
                "tasks list exception - ${e.message ?:"something went wrong"}".print()
                _reposlist.value = reposList.value.copy(
                    isLoading = false
                )
                e.printStackTrace()
            }
        }
    }

}