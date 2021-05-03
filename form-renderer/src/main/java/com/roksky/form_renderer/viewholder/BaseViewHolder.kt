package com.roksky.form_renderer.viewholder

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.roksky.form_renderer.listener.FormItemEditTextListener
import com.roksky.form_renderer.model.BaseElement

/**
 * Base ViewHolder for all other viewholders
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
open class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(
    itemView!!
), BaseViewHolderInterface<T> {
    override fun getListener(): FormItemEditTextListener? {
        return null
    }

    override fun bind(position: Int, element: BaseElement<T>, context: Context?) {}

    fun AppCompatTextView.markRequired() {
        text = buildSpannedString {
            append(text)
            color(Color.RED) { append(" *") } // Mind the space prefix.
        }
    }

}