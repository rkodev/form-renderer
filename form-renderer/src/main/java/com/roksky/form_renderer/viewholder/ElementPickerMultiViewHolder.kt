package com.roksky.form_renderer.viewholder

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.roksky.form_renderer.R
import com.roksky.form_renderer.listener.ReloadListener
import com.roksky.form_renderer.model.BaseElement
import com.roksky.form_renderer.model.ElementValue
import com.roksky.form_renderer.model.ElementPickerMulti
import java.util.*

/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */
class ElementPickerMultiViewHolder(v: View, context: Context?, reloadListener: ReloadListener) :
    BaseViewHolder<ElementValue<*>>(v) {
    private val mTextViewTitle: AppCompatTextView = v.findViewById(R.id.formElementTitle)
    private val mEditTextValue: AppCompatEditText = v.findViewById(R.id.formElementValue)
    private val mReloadListener: ReloadListener = reloadListener

    private var mElement: BaseElement<String>? = null
    private var mFormElementPickerMulti: ElementPickerMulti? = null
    private var mPosition = 0

    override fun bind(position: Int, element: BaseElement<ElementValue<*>>, context: Context?) {
        mPosition = position
        mFormElementPickerMulti = mElement as ElementPickerMulti?
        mTextViewTitle.text = element!!.title
        mEditTextValue.setText(element.value?.toDisplayValue())
        mEditTextValue.hint = element.hint
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

        if(element.readOnly)
            mEditTextValue.isEnabled = false

        if(element.isRequired) mTextViewTitle.markRequired()
    }

}