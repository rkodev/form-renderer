package com.roksky.form_renderer.viewholder

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement
import com.roksky.form_renderer.model.FormElementPickerDate
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewHolder for DatePicker
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormElementPickerDateViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<String>(v) {
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    private val mReloadListener: ReloadListener = reloadListener
    private val mCalendarCurrentDate = Calendar.getInstance()

    private var mDatePickerDialog: DatePickerDialog? = null
    private var mFormElement: BaseFormElement<String>? = null
    private var mPosition = 0

    /**
     * setting up date picker dialog listener
     */
    var date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mCalendarCurrentDate[Calendar.YEAR] = year
        mCalendarCurrentDate[Calendar.MONTH] = monthOfYear
        mCalendarCurrentDate[Calendar.DAY_OF_MONTH] = dayOfMonth
        val myFormatDate = (mFormElement as FormElementPickerDate?)!!.dateFormat
        val sdfDate = SimpleDateFormat(myFormatDate, Locale.US)
        val currentValue = mFormElement!!.value
        val newValue = sdfDate.format(mCalendarCurrentDate.time)

        // trigger event only if the value is changed
        if (currentValue != newValue) {
            mReloadListener.updateValue(mPosition, newValue)
        }
    }

    override fun bind(position: Int, formElement: BaseFormElement<String>, context: Context?) {
        mPosition = position
        mDatePickerDialog = DatePickerDialog(
            context!!,
            date,
            mCalendarCurrentDate[Calendar.YEAR],
            mCalendarCurrentDate[Calendar.MONTH],
            mCalendarCurrentDate[Calendar.DAY_OF_MONTH]
        )
        mTextViewTitle.text = formElement!!.title
        mEditTextValue.setText(formElement.value)
        mEditTextValue.hint = formElement.hint
        mEditTextValue.isFocusableInTouchMode = false
        mEditTextValue.setOnClickListener { mDatePickerDialog!!.show() }
        mTextViewTitle.setOnClickListener { mDatePickerDialog!!.show() }

        if(formElement.readOnly)
            mEditTextValue.isEnabled = false

        if(formElement.isRequired) mTextViewTitle.markRequired()
    }

}