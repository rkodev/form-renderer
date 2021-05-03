package com.roksky.form_renderer.listener

import com.roksky.form_renderer.model.BaseElement

/**
 * Callback to activity when any data in form adapter is changed
 */
interface OnFormElementValueChangedListener {
    fun onValueChanged(baseElement: BaseElement<*>)
}