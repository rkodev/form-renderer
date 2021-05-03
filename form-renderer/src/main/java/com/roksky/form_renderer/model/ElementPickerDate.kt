package com.roksky.form_renderer.model

import java.text.SimpleDateFormat
import java.util.*

class ElementPickerDate : BaseElement<String>(TYPE_PICKER_DATE) {
    // custom getter
    var dateFormat: String? = null
        set(value) {
            checkValidDateFormat(value)
            field = value
        }

    private fun checkValidDateFormat(format: String?) {
        try {
            SimpleDateFormat(format, Locale.US)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Date format is not correct: " + e.message)
        }
    }

    init {
        dateFormat = "dd/MM/yy"
    }
}