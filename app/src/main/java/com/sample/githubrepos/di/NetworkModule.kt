package com.sample.githubrepos.di

import com.sample.githubrepos.core.utils.network.ConnectivityManagerNetworkMonitor
import com.sample.githubrepos.core.utils.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor

}