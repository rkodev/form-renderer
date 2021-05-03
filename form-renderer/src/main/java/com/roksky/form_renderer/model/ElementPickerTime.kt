package com.roksky.form_renderer.model

import java.text.SimpleDateFormat
import java.util.*

class ElementPickerTime(id: String) : BaseElement<String>(TYPE_PICKER_TIME, id) {
    // custom getter
    var timeFormat : String? = null
        set(value) {
            checkValidTimeFormat(value)
            field = value
        }

    private fun checkValidTimeFormat(format: String?) {
        try {
            SimpleDateFormat(format, Locale.US)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Time format is not correct: " + e.message)
        }
    }

    init {
        timeFormat ="KK:mm a"
    }
}