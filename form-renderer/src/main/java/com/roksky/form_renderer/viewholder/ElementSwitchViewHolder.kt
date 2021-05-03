package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseElement
import com.roksky.form_renderer.model.ElementSwitch

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class ElementSwitchViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<String>(v) {
    private val mReloadListener: ReloadListener = reloadListener
    var mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    var mTextViewPositive: AppCompatTextView = v.findViewById(R.id.formElementPositiveText)
    var mTextViewNegative: AppCompatTextView = v.findViewById(R.id.formElementNegativeText)
    var mSwitch: SwitchCompat = v.findViewById(R.id.formElementSwitch)
    private var mElement: BaseElement<String>? = null
    private var mFormElementSwitch: ElementSwitch? = null
    private var mPosition = 0

    override fun bind(position: Int, element: BaseElement<String>, context: Context?) {
        mPosition = position
        mFormElementSwitch = mElement as ElementSwitch
        mTextViewTitle.text = mFormElementSwitch!!.title
        mTextViewPositive.text = mFormElementSwitch!!.positiveText
        mTextViewNegative.hint = mFormElementSwitch!!.negativeText
        mSwitch.setOnCheckedChangeListener { compoundButton, b ->
            mReloadListener.updateValue(
                position,
                if (b) mFormElementSwitch!!.positiveText else mFormElementSwitch!!.negativeText
            )
        }

        if(element.readOnly)
            mSwitch.isEnabled = false

        if(element.isRequired) mTextViewTitle.markRequired()
    }

}