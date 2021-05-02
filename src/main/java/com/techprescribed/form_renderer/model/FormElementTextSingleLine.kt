package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementTextSingleLine : BaseFormElement<String>() {

    companion object {
        fun createInstance(): FormElementTextSingleLine {
            return FormElementTextSingleLine().apply {
                type = TYPE_EDITTEXT_TEXT_SINGLELINE
            }
        }
    }
}