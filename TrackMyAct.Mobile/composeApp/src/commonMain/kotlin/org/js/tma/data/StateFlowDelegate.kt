package org.js.tma.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.reflect.KProperty

@Immutable
class StateFlowDelegate<T>(initialValue: T) {
    val mutable: MutableStateFlow<T> = MutableStateFlow(initialValue)

    val value: StateFlow<T> = mutable.asStateFlow()

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        mutable.value = value
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): MutableStateFlow<T> {
        return mutable
    }
}

fun <T> stateFlow(initialValue: T) = StateFlowDelegate(initialValue)

@Composable
fun <T> MutableStateFlow<T>.stateValue() = this.collectAsState().value