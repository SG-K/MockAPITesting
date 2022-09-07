package com.sample.githubrepos.di

import com.sample.githubrepos.core.ideling_resources.ComposeCoutingIdleingResource
import com.sample.githubrepos.core.ideling_resources.ComposeOkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IdlingResourceModule {

    @Singleton
    @Provides
    fun getCountingIdeleReousece() : ComposeCoutingIdleingResource {
        return ComposeCoutingIdleingResource("GET_REPOS")
    }

    @Singleton
    @Provides
    fun getComposeOkhttpResourceIdling(okHttpClient: OkHttpClient) : ComposeOkHttp3IdlingResource {
        return ComposeOkHttp3IdlingResource().create("okhttp", okHttpClient)
    }

}