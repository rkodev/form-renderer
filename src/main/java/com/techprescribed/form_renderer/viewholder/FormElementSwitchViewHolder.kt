package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement
import com.roksky.form_renderer.model.FormElementSwitch

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormElementSwitchViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<String>(v) {
    private val mReloadListener: ReloadListener = reloadListener
    var mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    var mTextViewPositive: AppCompatTextView = v.findViewById(R.id.formElementPositiveText)
    var mTextViewNegative: AppCompatTextView = v.findViewById(R.id.formElementNegativeText)
    var mSwitch: SwitchCompat = v.findViewById(R.id.formElementSwitch)
    private var mFormElement: BaseFormElement<String>? = null
    private var mFormElementSwitch: FormElementSwitch? = null
    private var mPosition = 0

    override fun bind(position: Int, formElement: BaseFormElement<String>, context: Context?) {
        mPosition = position
        mFormElementSwitch = mFormElement as FormElementSwitch
        mTextViewTitle.text = mFormElementSwitch!!.title
        mTextViewPositive.text = mFormElementSwitch!!.positiveText
        mTextViewNegative.hint = mFormElementSwitch!!.negativeText
        mSwitch.setOnCheckedChangeListener { compoundButton, b ->
            mReloadListener.updateValue(
                position,
                if (b) mFormElementSwitch!!.positiveText else mFormElementSwitch!!.negativeText
            )
        }

        if(formElement!!.readOnly)
            mSwitch.isEnabled = false

        if(formElement!!.isRequired) mTextViewTitle.markRequired()
    }

}