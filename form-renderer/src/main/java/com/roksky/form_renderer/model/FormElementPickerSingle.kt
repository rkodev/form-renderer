package com.roksky.form_renderer.model

import androidx.arch.core.util.Function
import java.util.*

/**
 * Created by Riddhi - Rudra on 28-Jul-17.
 */
class FormElementPickerSingle : BaseFormElement<ElementValue<*>>() {
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


    companion object {
        fun createInstance(): FormElementPickerSingle {
            return FormElementPickerSingle().apply {
                type = TYPE_PICKER_SINGLE
                pickerTitle = "Pick one"
            }
        }
    }
}