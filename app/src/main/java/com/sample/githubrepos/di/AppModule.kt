package com.sample.githubrepos.di

import android.content.Context
import com.sample.githubrepos.BuildConfig
import com.sample.githubrepos.core.ideling_resources.ComposeCoutingIdleingResource
import com.sample.githubrepos.core.ideling_resources.ComposeOkHttp3IdlingResource
import com.sample.githubrepos.core.utils.GitHubConstants
import com.sample.githubrepos.core.utils.network.ConnectivityManagerNetworkMonitor
import com.sample.githubrepos.core.utils.network.NetworkMonitor
import com.sample.githubrepos.feature_github_repos.data.data_source.GitHubAPIService
import com.sample.githubrepos.feature_github_repos.data.repository.GitHubRepositoryImpl
import com.sample.githubrepos.feature_github_repos.domain.repository.GitHubRepository
import com.sample.githubrepos.feature_github_repos.domain.use_case.GetGitHubReposUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GitHubAPIService = retrofit.create(GitHubAPIService::class.java)


    @Provides
    @Singleton
    fun provideGitHubRepoRepository(gitHubAPIService: GitHubAPIService): GitHubRepository {
        return GitHubRepositoryImpl(gitHubAPIService)
    }

    @Provides
    @Singleton
    fun provideGitHubRepoUseCase(repository: GitHubRepository): GetGitHubReposUseCase {
        return GetGitHubReposUseCase(repository)
    }
//
//    @Provides
//    fun bindsNetworkMonitor(
//        @ApplicationContext context: Context
//    ): NetworkMonitor{
//        return ConnectivityManagerNetworkMonitor(context)
//    }

}