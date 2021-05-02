package com.roksky.form_renderer.model

/**
 * Object for header of the form lists
 * Created by Adib on 18-Apr-17.
 */
class FormHeader : BaseFormElement<String>() {


    companion object {
        fun createInstance(title: String?): FormHeader {
            return FormHeader().apply {
                type = TYPE_HEADER
                this.title = title
            }
        }
    }
}