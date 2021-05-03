package com.roksky.form_renderer.model

import java.util.*

class ElementPickerMulti : BaseElement<String>(TYPE_PICKER_MULTI) {

    var pickerTitle: String? = null
    var options: List<String> = ArrayList()
    var optionsSelected: List<String> = ArrayList()
    var positiveText = "Ok"
    var negativeText = "Cancel"
}