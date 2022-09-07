package com.sample.githubrepos.core.ideling_resources

import androidx.annotation.CheckResult
import androidx.compose.ui.test.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import okhttp3.Dispatcher
import okhttp3.OkHttpClient


class ComposeOkHttp3IdlingResource : IdlingResource {

    private var name: String? = null
    private var dispatcher: Dispatcher? = null

    @Volatile
    var callback: ResourceCallback? = null

    /**
     * Create a new [IdlingResource] from `client` as `name`. You must register
     * this instance using `Espresso.registerIdlingResources`.
     */
    public fun create(name: String, client: OkHttpClient) = ComposeOkHttp3IdlingResource(name, client.dispatcher)

    constructor() {}

    private constructor(name: String, dispatcher: Dispatcher)  {
        this.name = name
        this.dispatcher = dispatcher
        dispatcher.idleCallback = Runnable {
            val callback: ResourceCallback? = this.callback
            callback?.onTransitionToIdle()
        }
    }


    override val isIdleNow: Boolean
        get() = (dispatcher?.runningCallsCount() ?: 0) == 0;

}