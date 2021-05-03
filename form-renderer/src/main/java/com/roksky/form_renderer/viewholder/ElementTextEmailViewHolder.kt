package com.roksky.form_renderer.viewholder

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.FormItemEditTextListener
import com.roksky.form_renderer.model.BaseElement

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class ElementTextEmailViewHolder(v: View, listener: FormItemEditTextListener) :
    BaseViewHolder<String>(v) {
    var mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    var mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    var mFormCustomEditTextListener: FormItemEditTextListener = listener

    override fun getListener(): FormItemEditTextListener? {
        return mFormCustomEditTextListener
    }

    override fun bind(position: Int, element: BaseElement<String>, context: Context?) {
        mTextViewTitle.text = element!!.title
        mEditTextValue.setText(element.value)
        mEditTextValue.hint = element.hint
        itemView.setOnClickListener {
            mEditTextValue.requestFocus()
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(mEditTextValue, InputMethodManager.SHOW_IMPLICIT)
        }

        if(element.readOnly)
            mEditTextValue.isEnabled = false

        if(element.isRequired) mTextViewTitle.markRequired()
    }

    init {
        mEditTextValue.addTextChangedListener(mFormCustomEditTextListener)
        mEditTextValue.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
    }
}