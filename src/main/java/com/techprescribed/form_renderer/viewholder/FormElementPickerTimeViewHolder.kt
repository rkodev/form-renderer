package com.roksky.form_renderer.viewholder

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement
import com.roksky.form_renderer.model.FormElementPickerTime
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormElementPickerTimeViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<String>(v) {
    private var mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private var mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    private var mTimePickerDialog: TimePickerDialog
    private var mCalendarCurrentTime: Calendar = Calendar.getInstance()
    private var mReloadListener: ReloadListener = reloadListener
    private var mFormElement: BaseFormElement<String>? = null
    private var mPosition = 0

    /**
     * setting up time picker dialog listener
     */
    var time = OnTimeSetListener { view, hourOfDay, minute ->
        mCalendarCurrentTime[Calendar.HOUR_OF_DAY] = hourOfDay
        mCalendarCurrentTime[Calendar.MINUTE] = minute
        val myFormatTime = (mFormElement as FormElementPickerTime?)!!.timeFormat // custom format
        val sdfTime = SimpleDateFormat(myFormatTime, Locale.US)
        val currentValue = mFormElement!!.value
        val newValue = sdfTime.format(mCalendarCurrentTime.time)

        // trigger event only if the value is changed
        if (currentValue != newValue) {
            mReloadListener.updateValue(mPosition, newValue)
        }
    }

    override fun bind(position: Int, formElement: BaseFormElement<String>, context: Context?) {
        mPosition = position
        mTextViewTitle.text = formElement!!.title
        mEditTextValue.setText(formElement.value)
        mEditTextValue.hint = formElement.hint
        mEditTextValue.isFocusableInTouchMode = false
        mEditTextValue.setOnClickListener { mTimePickerDialog.show() }
        mTextViewTitle.setOnClickListener { mTimePickerDialog.show() }

        if(formElement.readOnly)
            mEditTextValue.isEnabled = false

        if(formElement.isRequired) mTextViewTitle.markRequired()
    }

    init {
        mTimePickerDialog = TimePickerDialog(
            context,
            time,
            mCalendarCurrentTime[Calendar.HOUR],
            mCalendarCurrentTime[Calendar.MINUTE],
            false
        )
    }
}