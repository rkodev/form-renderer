package com.roksky.form_renderer.model

import com.roksky.form_renderer.viewholder.GpsLocation

class FormElementLocationPicker : BaseFormElement<GpsLocation>() {

    companion object {
        fun createInstance(): FormElementLocationPicker {
            return FormElementLocationPicker().apply {
                type = TYPE_LOCATION
            }
        }
    }
}