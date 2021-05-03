package com.roksky.form_renderer.model

import androidx.arch.core.util.Function
import java.util.*

class ElementPickerSingle(id: String) : BaseElement<ElementValue<*>>(TYPE_PICKER_SINGLE, id) {
    var pickerTitle: String? = null
    var options: List<ElementValue<*>> = ArrayList()
    var optionsSelected: List<ElementValue<*>> = ArrayList()

    fun <T> composeOptions(
        values: List<T>?,
        displayFunction: Function<T, String>
    ): List<ElementValue<T>> {
        if(values == null) return emptyList()

        return values.map {
            object : ElementValue<T> {
                override fun toDisplayValue(): String {
                    return displayFunction.apply(it)
                }

                override fun value(): T {
                    return it
                }

            }
        }
    }

    init {
        pickerTitle = "Pick one"
    }
}