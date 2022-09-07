package com.sample.githubrepos.core.ideling_resources

import androidx.compose.ui.test.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource



class ComposeCoutingIdleingResource(name : String) : IdlingResource {

    private val countingIdlingResource : CountingIdlingResource =
        CountingIdlingResource(name)

    override val isIdleNow: Boolean
        get() = countingIdlingResource.isIdleNow

    fun increment() = countingIdlingResource
        .increment()

    fun decrement() = countingIdlingResource
        .decrement()

}