package com.roksky.form_renderer.viewholder

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseElement
import com.roksky.form_renderer.model.ElementPickerDate
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewHolder for DatePicker
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class ElementPickerDateViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<String>(v) {
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    private val mReloadListener: ReloadListener = reloadListener
    private val mCalendarCurrentDate = Calendar.getInstance()

    private var mDatePickerDialog: DatePickerDialog? = null
    private var mElement: BaseElement<String>? = null
    private var mPosition = 0

    /**
     * setting up date picker dialog listener
     */
    var date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mCalendarCurrentDate[Calendar.YEAR] = year
        mCalendarCurrentDate[Calendar.MONTH] = monthOfYear
        mCalendarCurrentDate[Calendar.DAY_OF_MONTH] = dayOfMonth
        val myFormatDate = (mElement as ElementPickerDate?)!!.dateFormat
        val sdfDate = SimpleDateFormat(myFormatDate, Locale.US)
        val currentValue = mElement!!.value
        val newValue = sdfDate.format(mCalendarCurrentDate.time)

        // trigger event only if the value is changed
        if (currentValue != newValue) {
            mReloadListener.updateValue(mPosition, newValue)
        }
    }

    override fun bind(position: Int, element: BaseElement<String>, context: Context?) {
        mPosition = position
        mDatePickerDialog = DatePickerDialog(
            context!!,
            date,
            mCalendarCurrentDate[Calendar.YEAR],
            mCalendarCurrentDate[Calendar.MONTH],
            mCalendarCurrentDate[Calendar.DAY_OF_MONTH]
        )
        mTextViewTitle.text = element!!.title
        mEditTextValue.setText(element.value)
        mEditTextValue.hint = element.hint
        mEditTextValue.isFocusableInTouchMode = false
        mEditTextValue.setOnClickListener { mDatePickerDialog!!.show() }
        mTextViewTitle.setOnClickListener { mDatePickerDialog!!.show() }

        if(element.readOnly)
            mEditTextValue.isEnabled = false

        if(element.isRequired) mTextViewTitle.markRequired()
    }

}