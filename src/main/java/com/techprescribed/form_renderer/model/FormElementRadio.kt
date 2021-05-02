package com.roksky.form_renderer.model

import java.util.*

class FormElementRadio : BaseFormElement<ElementValue<*>>() {
    var pickerTitle: String? = null
    var options: List<ElementValue<*>> = ArrayList()
    var optionsSelected: List<ElementValue<*>> = ArrayList()

    fun composeOptions(values: List<String>): List<ElementValue<String>> {
        return values.map {
            object : ElementValue<String> {
                override fun toDisplayValue(): String {
                    return it
                }

                override fun value(): String {
                    return it
                }

            }
        }
    }

    companion object {
        fun createInstance(): FormElementRadio {
            return FormElementRadio().apply {
                type = TYPE_RADIO
                pickerTitle = "Pick one"
            }
        }
    }
}