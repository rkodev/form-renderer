package com.roksky.form_renderer.viewholder

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.Consumer
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement


class FormElementLocationPickerViewHolder(
    v: View,
    val activity: Activity,
    reloadListener: ReloadListener
) : BaseViewHolder<GpsLocation>(v) {
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)

    private val mReloadListener: ReloadListener = reloadListener
    private var mPosition = 0

    override fun bind(position: Int, formElement: BaseFormElement<GpsLocation>, context: Context?) {
        mPosition = position

        mTextViewTitle.text = formElement!!.title

        if(formElement.value != null){
            val locStr =
                "Lat : " + formElement.value!!.getLatitude()
                    .toString() + " , Lon : " + formElement.value!!.getLatitude().toString()

            mEditTextValue.setText(locStr)
        }
        mEditTextValue.hint = formElement.hint
        mEditTextValue.isFocusableInTouchMode = false

        val consumer = Consumer<GpsLocation> { location ->
            val locStr =
                "Lat : " + location.getLatitude()
                    .toString() + " , Lon : " + location.getLatitude().toString()

            if (formElement.value != location)
                mReloadListener.updateValue(mPosition, location)

            mEditTextValue.setText(locStr)
            formElement.value = location
        }

        val dialog = GpsDialog(
            activity,
            consumer,
        )

        mEditTextValue.setOnClickListener { dialog.show() }

        if(formElement.readOnly)
            mEditTextValue.isEnabled = false

        if (formElement.isRequired) mTextViewTitle.markRequired()
    }

}