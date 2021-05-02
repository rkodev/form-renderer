package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementTextMultiLine : BaseFormElement<String>() {

    companion object {
        fun createInstance(): FormElementTextMultiLine {
            return FormElementTextMultiLine().apply {
                type = TYPE_EDITTEXT_TEXT_MULTILINE
            }
        }
    }
}