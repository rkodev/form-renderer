package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.model.BaseFormElement

/**
 * ViewHolder for Header
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormElementHeader(v: View) : BaseViewHolder<String>(v) {
    var mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)

    override fun bind(position: Int, formElement: BaseFormElement<String>, context: Context?) {
        mTextViewTitle.text = formElement!!.title
    }

}