package com.roksky.form_renderer.viewholder

import android.content.Context
import com.roksky.form_renderer.listener.FormItemEditTextListener
import com.roksky.form_renderer.model.BaseElement

/**
 * Base ViewHolder method instance
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
interface BaseViewHolderInterface<T> {
    fun getListener(): FormItemEditTextListener?
    fun bind(position: Int, element: BaseElement<T>, context: Context?)
}