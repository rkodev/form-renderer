package com.roksky.form_renderer.model

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementTextPhone : BaseFormElement<String>() {

    companion object {
        fun createInstance(): FormElementTextPhone {
            return FormElementTextPhone().apply {
                type = TYPE_EDITTEXT_PHONE
            }
        }
    }
}