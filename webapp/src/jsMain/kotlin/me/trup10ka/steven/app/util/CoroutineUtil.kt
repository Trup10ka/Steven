package me.trup10ka.steven.app.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun launchInMainScope(block: suspend () -> Unit)
{
    CoroutineScope(Dispatchers.Main)
        .launch { block() }
}
