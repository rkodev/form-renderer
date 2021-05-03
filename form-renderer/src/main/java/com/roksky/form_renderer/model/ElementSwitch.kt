package com.roksky.form_renderer.model

class ElementSwitch(id: String) : BaseElement<String>(TYPE_SWITCH, id) {

    var positiveText: String? = null
    var negativeText: String? = null

    // custom setters
    fun setSwitchTexts(positiveText: String?, negativeText: String?): ElementSwitch {
        this.positiveText = positiveText
        this.negativeText = negativeText
        return this
    }

}