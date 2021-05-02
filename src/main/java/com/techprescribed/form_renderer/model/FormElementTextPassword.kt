package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementTextPassword : BaseFormElement<String>() {

    companion object {
        fun createInstance(): FormElementTextPassword {
            return FormElementTextPassword().apply {
                type = TYPE_EDITTEXT_PASSWORD
            }
        }
    }
}