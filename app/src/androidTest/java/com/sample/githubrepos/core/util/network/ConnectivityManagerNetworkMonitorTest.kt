package com.sample.githubrepos.core.util.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import com.sample.githubrepos.core.utils.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.junit.Assert.*
import javax.inject.Inject


interface DummyCallbacks{
    fun updateStatus(status : Boolean)
}

class AlwaysOnlineNetworkMonitor @Inject constructor() : NetworkMonitor {
    val scope = CoroutineScope(Job())
    private val connectivityFlow = MutableStateFlow(false)

    override val isOnline: Flow<Boolean> = connectivityFlow

    /**
     * A test-only API to set the connectivity state from tests.
     */
    override fun setConnected(isConnected: Boolean) {
//        scope.launch {
//            connectivityFlow.emit(isConnected)
//            connectivityFlow.update { isConnected }
//        }
        println("flow_value_test - before - ${connectivityFlow.value}")
        connectivityFlow.value = isConnected
        println("flow_value_test - after - ${connectivityFlow.value}")
    }


}
