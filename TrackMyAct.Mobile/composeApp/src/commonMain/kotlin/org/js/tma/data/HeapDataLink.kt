package org.js.tma.data

import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Stable

sealed interface HeapDataLink<T> {

    fun setData(data: T)

    fun getData(): T?

}

@Stable
class HeapStringLink : HeapDataLink<String> {

    private var data: String? = null

    override fun setData(data: String) {
        this.data = data
    }

    override fun getData(): String? {
        return data
    }
}

@Stable
class HeapDateLink : HeapDataLink<DatePickerState> {

    private var data: DatePickerState? = null

    override fun setData(data: DatePickerState) {
        this.data = data
    }

    override fun getData(): DatePickerState? {
        return data
    }
}