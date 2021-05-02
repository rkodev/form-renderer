package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementTextNumber : BaseFormElement<String>() {

    companion object {
        fun createInstance(): FormElementTextNumber {
            return FormElementTextNumber().apply {
                type = TYPE_EDITTEXT_NUMBER
            }
        }
    }
}