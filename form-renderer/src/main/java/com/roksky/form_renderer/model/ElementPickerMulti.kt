package com.roksky.form_renderer.model

import java.util.*

class ElementPickerMulti(id: String) : BaseElement<String>(TYPE_PICKER_MULTI, id) {

    var pickerTitle: String? = null
    var options: List<String> = ArrayList()
    var optionsSelected: List<String> = ArrayList()
    var positiveText = "Ok"
    var negativeText = "Cancel"
}