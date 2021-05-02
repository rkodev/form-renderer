package com.roksky.form_renderer.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementPickerDate : BaseFormElement<String>() {
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

    companion object {
        fun createInstance(): FormElementPickerDate {
            return FormElementPickerDate().apply {
                type = TYPE_PICKER_DATE
                dateFormat = "dd/MM/yy"
            }
        }
    }
}