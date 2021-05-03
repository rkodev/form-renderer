package com.roksky.form_renderer.model

class ElementSwitch : BaseElement<String>(TYPE_SWITCH) {

    var positiveText: String? = null
    var negativeText: String? = null

    // custom setters
    fun setSwitchTexts(positiveText: String?, negativeText: String?): ElementSwitch {
        this.positiveText = positiveText
        this.negativeText = negativeText
        return this
    }

}