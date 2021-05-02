package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseFormElement
import com.roksky.form_renderer.model.ElementValue
import com.roksky.form_renderer.model.FormElementPickerMulti
import java.util.*

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class FormElementPickerMultiViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<ElementValue<*>>(v) {
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    private val mReloadListener: ReloadListener = reloadListener

    private var mFormElement: BaseFormElement<String>? = null
    private var mFormElementPickerMulti: FormElementPickerMulti? = null
    private var mPosition = 0

    override fun bind(position: Int, formElement: BaseFormElement<ElementValue<*>>, context: Context?) {
        mPosition = position
        mFormElementPickerMulti = mFormElement as FormElementPickerMulti?
        mTextViewTitle.text = formElement!!.title
        mEditTextValue.setText(formElement.value?.toDisplayValue())
        mEditTextValue.hint = formElement.hint
        mEditTextValue.isFocusableInTouchMode = false

        // reformat the options in format needed
        val options = arrayOfNulls<CharSequence>(
            mFormElementPickerMulti!!.options.size
        )
        val optionsSelected = BooleanArray(mFormElementPickerMulti!!.options.size)
        val mSelectedItems: ArrayList<Int> = ArrayList<Int>()
        for (i in mFormElementPickerMulti!!.options.indices) {
            options[i] = mFormElementPickerMulti!!.options[i]
            optionsSelected[i] = false
            if (mFormElementPickerMulti!!.optionsSelected.contains(options[i])) {
                optionsSelected[i] = true
                mSelectedItems.add(i)
            }
        }

        // prepare the dialog
        val dialog = AlertDialog.Builder(
            context!!
        )
            .setTitle(mFormElementPickerMulti!!.pickerTitle)
            .setMultiChoiceItems(
                options, optionsSelected
            ) { dialog, which, isChecked ->
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    mSelectedItems.add(which)
                } else if (mSelectedItems.contains(which)) {
                    // Else, if the item is already in the array, remove it
                    mSelectedItems.remove(Integer.valueOf(which))
                }
            } // Set the action buttons
            .setPositiveButton(mFormElementPickerMulti!!.positiveText) { dialog, id ->
                var s: String? = ""
                for (i in mSelectedItems.indices) {
                    s += options[mSelectedItems[i]]
                    if (i < mSelectedItems.size - 1) {
                        s += ", "
                    }
                }
                mEditTextValue.setText(s)
                mReloadListener.updateValue(position, s)
            }
            .setNegativeButton(mFormElementPickerMulti!!.negativeText, null)
            .create()
        mEditTextValue.setOnClickListener { dialog.show() }
        mTextViewTitle.setOnClickListener { dialog.show() }

        if(formElement.readOnly)
            mEditTextValue.isEnabled = false

        if(formElement.isRequired) mTextViewTitle.markRequired()
    }

}