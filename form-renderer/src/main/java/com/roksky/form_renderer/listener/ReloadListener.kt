package com.roksky.form_renderer.listener

/**
 * Callback for if any data in form element list is changed
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
interface ReloadListener {
    fun <T> updateValue(position: Int, updatedValue: T?)
}