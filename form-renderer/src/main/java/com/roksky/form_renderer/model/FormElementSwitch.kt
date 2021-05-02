package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementSwitch : BaseFormElement<String>() {

    var positiveText: String? = null
    var negativeText: String? = null

    // custom setters
    fun setSwitchTexts(positiveText: String?, negativeText: String?): FormElementSwitch {
        this.positiveText = positiveText
        this.negativeText = negativeText
        return this
    }

    companion object {
        fun createInstance(): FormElementSwitch {
            return FormElementSwitch().apply {
                type = TYPE_SWITCH
            }
        }
    }
}