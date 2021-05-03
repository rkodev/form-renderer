package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.model.BaseElement

/**
 * ViewHolder for Header
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class ElementHeaderViewHolder(v: View) : BaseViewHolder<String>(v) {
    var mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)

    override fun bind(position: Int, element: BaseElement<String>, context: Context?) {
        mTextViewTitle.text = element!!.title
    }

}