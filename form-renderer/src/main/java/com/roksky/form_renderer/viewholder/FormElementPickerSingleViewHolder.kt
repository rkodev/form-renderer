package com.roksky.form_renderer.viewholder

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement
import com.roksky.form_renderer.model.ElementValue
import com.roksky.form_renderer.model.FormElementPickerSingle

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormElementPickerSingleViewHolder(
    v: View,
    context: Context?,
    reloadListener: ReloadListener
) : BaseViewHolder<ElementValue<*>>(v) {
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    private val mReloadListener: ReloadListener = reloadListener
    private lateinit var mFormElementPickerSingle: FormElementPickerSingle
    private var mPosition = 0

    override fun bind(position: Int, formElement: BaseFormElement<ElementValue<*>>, context: Context?) {
        mPosition = position
        mFormElementPickerSingle = formElement as FormElementPickerSingle
        mTextViewTitle.text = formElement!!.title
        mEditTextValue.setText(formElement.value?.toDisplayValue())
        mEditTextValue.hint = formElement.hint
        mEditTextValue.isFocusableInTouchMode = false

        // reformat the options in format needed
        val options = arrayOfNulls<String>(
            mFormElementPickerSingle.options.size
        )
        for (i in mFormElementPickerSingle.options.indices) {
            options[i] = mFormElementPickerSingle.options[i].toDisplayValue()
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle(mFormElementPickerSingle.pickerTitle)
            .setItems(options) { dialog, which ->
                mEditTextValue.setText(options[which])
                mFormElementPickerSingle.value = mFormElementPickerSingle.options[which]
                mReloadListener.updateValue(position, mFormElementPickerSingle.options[which])
            }
            .create()
        mEditTextValue.setOnClickListener { dialog.show() }
        mTextViewTitle.setOnClickListener { dialog.show() }

        if(formElement.readOnly)
            mEditTextValue.isEnabled = false

        if(formElement.isRequired) mTextViewTitle.markRequired()
    }

}