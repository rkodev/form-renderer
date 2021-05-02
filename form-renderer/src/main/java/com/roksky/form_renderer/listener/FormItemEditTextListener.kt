package com.roksky.form_renderer.listener

import android.text.Editable
import android.text.TextWatcher
import com.roksky.form_renderer.adapter.FormAdapter

/**
 * Edit text listener for form element edit texts
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormItemEditTextListener(private val formAdapter: FormAdapter) : TextWatcher {
    private var position = 0
    fun updatePosition(position: Int) {
        this.position = position
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {

        // get current form element, existing value and new value
        val baseFormElement = formAdapter.dataset[position]
        val currentValue = baseFormElement.value
        val newValue = charSequence.toString()

        // trigger event only if the value is changed
        if (currentValue != newValue) {
            baseFormElement.value = newValue
            if (formAdapter.valueChangeListener != null) formAdapter.valueChangeListener.onValueChanged(
                baseFormElement
            )
        }
    }

    override fun afterTextChanged(editable: Editable) {}
}