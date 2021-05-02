package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementTextEmail : BaseFormElement<String>() {

    companion object {
        fun createInstance(): FormElementTextEmail {
            return FormElementTextEmail().apply {
                type = TYPE_EDITTEXT_EMAIL
            }
        }
    }
}