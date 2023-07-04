package com.sample.githubrepos.feature_github_repos.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubrepos.core.ideling_resources.ComposeCoutingIdleingResource
import com.sample.githubrepos.core.utils.network.NetworkMonitor
import com.sample.githubrepos.core.utils.print
import com.sample.githubrepos.feature_github_repos.domain.model.GitHubRepoData
import com.sample.githubrepos.feature_github_repos.domain.use_case.GetGitHubReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GitHubReposViewModel @Inject constructor(
    val monitor: NetworkMonitor,
    private val gitHubReposUseCase: GetGitHubReposUseCase,
    private val composeCoutingIdleingResource: ComposeCoutingIdleingResource
) : ViewModel() {

    private val _reposlist = mutableStateOf(GitHubReposState(emptyList(), true))
    val reposList: State<GitHubReposState> = _reposlist

    val isOnlineViewmodel : StateFlow<Boolean> = monitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )


    init {
        viewModelScope.launch {

            "tasks list initiating".print()
            "monitoring_api_call - ${isOnlineViewmodel.value}".print()
            if (isOnlineViewmodel.value){
                makeAPICall()
            } else{
                _reposlist.value = reposList.value.copy(
                    isLoading = false
                )
            }

//            monitor.isOnline.collectLatest{
//                "monitoring_api_call - ${it}".print()
//                if (it){
//                    makeAPICall()
//                } else{
//                    _reposlist.value = reposList.value.copy(
//                        isLoading = false
//                    )
//                }
//            }

        }
    }

    suspend fun makeAPICall(){
        _reposlist.value = reposList.value.copy(
            isLoading = true
        )
        try {
            composeCoutingIdleingResource.increment()
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
        } finally {
            if (!composeCoutingIdleingResource.isIdleNow){
                composeCoutingIdleingResource.decrement()
            }
        }
    }

}