package com.sample.githubrepos.core.utils.network

import kotlinx.coroutines.flow.Flow

/**
 * Utility for reporting app connectivity status
 */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
    fun setConnected(status : Boolean)
}
