package com.roksky.form_renderer.model

import java.util.*

class ElementRadio : BaseElement<ElementValue<*>>(TYPE_RADIO) {
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

    init {
        pickerTitle = "Pick one"
    }

}