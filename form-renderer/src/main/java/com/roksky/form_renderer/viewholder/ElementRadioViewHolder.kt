package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseElement
import com.roksky.form_renderer.model.ElementValue
import com.roksky.form_renderer.model.ElementRadio

class ElementRadioViewHolder(
    v: View,
    context: Context?,
    reloadListener: ReloadListener
) : BaseViewHolder<ElementValue<*>>(v){
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val radioGroup: RadioGroup = v.findViewById(R.id.radio_group)
    private val mReloadListener: ReloadListener = reloadListener
    private lateinit var mFormElementPickerSingle: ElementRadio
    private var mPosition = 0

    override fun bind(
        position: Int,
        element: BaseElement<ElementValue<*>>,
        context: Context?
    ) {
        mPosition = position
        mFormElementPickerSingle = element as ElementRadio
        mTextViewTitle.text = element.title

        // remove all elements
        radioGroup.removeAllViews()

        // add all elements
        for (optionElement in mFormElementPickerSingle.options) {
            val radioBtn = RadioButton(radioGroup.context)
            radioBtn.text = optionElement.toDisplayValue()
            radioBtn.setOnClickListener {
                mFormElementPickerSingle.value = optionElement
                mReloadListener.updateValue(position, optionElement)
            }

            if(element.value?.toDisplayValue().equals(optionElement.toDisplayValue()))
                radioBtn.isChecked = true

            radioGroup.addView(radioBtn)
        }

        if(element.readOnly)
            radioGroup.isEnabled = false

        if (element.isRequired) mTextViewTitle.markRequired()
    }
}