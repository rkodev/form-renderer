package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement
import com.roksky.form_renderer.model.ElementValue
import com.roksky.form_renderer.model.FormElementRadio

class FormElementRadioViewHolder(
    v: View,
    context: Context?,
    reloadListener: ReloadListener
) : BaseViewHolder<ElementValue<*>>(v){
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val radioGroup: RadioGroup = v.findViewById(R.id.radio_group)
    private val mReloadListener: ReloadListener = reloadListener
    private lateinit var mFormElementPickerSingle: FormElementRadio
    private var mPosition = 0

    override fun bind(
        position: Int,
        formElement: BaseFormElement<ElementValue<*>>,
        context: Context?
    ) {
        mPosition = position
        mFormElementPickerSingle = formElement as FormElementRadio
        mTextViewTitle.text = formElement.title

        // remove all elements
        radioGroup.removeAllViews()

        // add all elements
        for (element in mFormElementPickerSingle.options) {
            val radioBtn = RadioButton(radioGroup.context)
            radioBtn.text = element.toDisplayValue()
            radioBtn.setOnClickListener {
                mFormElementPickerSingle.value = element
                mReloadListener.updateValue(position, element)
            }

            if(formElement.value?.toDisplayValue().equals(element.toDisplayValue()))
                radioBtn.isChecked = true

            radioGroup.addView(radioBtn)
        }

        if(formElement.readOnly)
            radioGroup.isEnabled = false

        if (formElement.isRequired) mTextViewTitle.markRequired()
    }
}