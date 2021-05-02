package com.roksky.form_renderer.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementPickerTime : BaseFormElement<String>() {
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

    companion object {
        fun createInstance(): FormElementPickerTime {
            return FormElementPickerTime().apply {
                type = TYPE_PICKER_TIME
                timeFormat ="KK:mm a"
            }
        }
    }
}