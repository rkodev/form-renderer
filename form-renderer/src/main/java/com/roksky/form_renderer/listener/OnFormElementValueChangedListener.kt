package com.roksky.form_renderer.listener

import com.roksky.form_renderer.model.BaseFormElement

/**
 * Callback to activity when any data in form adapter is changed
 */
interface OnFormElementValueChangedListener {
    fun onValueChanged(baseFormElement: BaseFormElement<*>)
}