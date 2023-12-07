package com.lcabral.artseventh.core.data.hub.commomTest

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

private val SCHEDULER_INSTANCE = Schedulers.trampoline()

