package com.roksky.form_renderer.model

import java.util.*

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementPickerMulti : BaseFormElement<String>() {

    var pickerTitle: String? = null
    var options: List<String> = ArrayList()
    var optionsSelected: List<String> = ArrayList()
    var positiveText = "Ok"
    var negativeText = "Cancel"

    companion object {
        fun createInstance(): FormElementPickerMulti {
            return FormElementPickerMulti().apply {
                type = TYPE_PICKER_MULTI
            }
        }
    }
}