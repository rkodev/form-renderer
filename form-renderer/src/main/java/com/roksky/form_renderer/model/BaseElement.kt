package com.roksky.form_renderer.model

/**
 * Created by Adib on 16-Apr-17.
 */
open class BaseElement<T>(val type: Int) {
    // getters
    // private variables

    // unique tag to identify the object
    var id : String? = null

    // unique tag to identify the object
    var tag = 0

    // title to be shown on left
    var title : String? = null

    // value to set is the field is required
    var isRequired = false

    var value: T? = null

    var hint: String = ""

    var readOnly: Boolean = false

    override fun toString(): String {
        return "BaseFormElement{" +
                "mTag=" + tag +
                ", mType=" + type +
                ", mTitle='" + title + '\'' +
                ", mValue='" + value + '\'' +
                ", mHint='" + hint + '\'' +
                ", mRequired=" + isRequired +
                '}'
    }

    companion object {
        // different types for the form elements
        const val TYPE_HEADER = 0
        const val TYPE_EDITTEXT_TEXT_SINGLELINE = 1
        const val TYPE_EDITTEXT_TEXT_MULTILINE = 2
        const val TYPE_EDITTEXT_NUMBER = 3
        const val TYPE_EDITTEXT_EMAIL = 4
        const val TYPE_EDITTEXT_PHONE = 5
        const val TYPE_EDITTEXT_PASSWORD = 6
        const val TYPE_PICKER_DATE = 7
        const val TYPE_PICKER_TIME = 8
        const val TYPE_PICKER_SINGLE = 9
        const val TYPE_PICKER_MULTI = 10
        const val TYPE_SWITCH = 11
        const val TYPE_LOCATION = 12
        const val TYPE_RADIO = 13
    }
}