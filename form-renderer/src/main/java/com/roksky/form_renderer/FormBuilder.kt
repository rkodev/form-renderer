package com.roksky.form_renderer

import android.app.Activity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roksky.form_renderer.adapter.FormAdapter
import com.roksky.form_renderer.listener.OnFormElementValueChangedListener
import com.roksky.form_renderer.model.BaseElement
import java.lang.IllegalArgumentException

/**
 * Wrapper class around the adapter to assist in building form
 * Created by Adib on 16-Apr-17.
 */
class FormBuilder {
    private lateinit var mFormAdapter: FormAdapter

    fun notifyItemChanged(position: Int) {
        mFormAdapter.notifyItemChanged(position)
    }


    fun <T> getItemPosition(element: BaseElement<T>): Int {
        return mFormAdapter.getItemPosition(element)
    }

    fun <T> setElementValue(elementId: String, value: T) {
        val element =
            getFormElement<BaseElement<T>>(
                elementId
            )

        element.value = value
        notifyItemChanged(getItemPosition(element))
    }

    fun <T> getElementValue(elementId: String): T? {
        return getFormElement<BaseElement<T>>(
            elementId
        ).value
    }

    inline fun <reified T> getElementValue(element: BaseElement<*>): T? {
        return if(element.value == null || element.value is T?) element.value as T? else throw IllegalArgumentException("Value is not of type")
    }

    /**
     * constructor without listener callback for changed values
     *
     * @param context
     * @param recyclerView
     */
    constructor(activity: Activity, recyclerView: RecyclerView) {
        initializeFormBuildHelper(activity, recyclerView, null)
    }

    /**
     * constructor with listener callback for changed values
     *
     * @param context
     * @param recyclerView
     */
    constructor(
        activity: Activity,
        recyclerView: RecyclerView,
        listener: OnFormElementValueChangedListener?
    ) {
        initializeFormBuildHelper(activity, recyclerView, listener)
    }

    /**
     * private method for initializing form build helper
     *
     * @param context
     * @param recyclerView
     * @param listener
     */
    private fun initializeFormBuildHelper(
        activity: Activity,
        recyclerView: RecyclerView,
        listener: OnFormElementValueChangedListener?
    ) {

        // initialize form adapter
        mFormAdapter = FormAdapter(activity, listener)

        // set up the recyclerview with adapter
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.stackFromEnd = false
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = mFormAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    /**
     * add list of form elements to be shown
     *
     * @param baseElements
     */
    fun addFormElements(baseElements: List<BaseElement<*>?>?) {
        mFormAdapter.addElements(baseElements)
    }

    /**
     * get value of specific form element
     *
     * @param tag
     * @return
     */
    fun getFormElement(tag: Int): BaseElement<*> {
        return mFormAdapter.getValueAtTag(tag)
    }

    /**
     * get value of specific form element
     *
     * @param tag
     * @return
     */
    fun <T : BaseElement<*>> getFormElement(id: String): T {
        return mFormAdapter.getValueAtId(id) as T
    }

    /**
     * return true if the form is valid
     *
     * @return
     */
    val isValidForm: Boolean
        get() {
            for (i in 0 until mFormAdapter.itemCount) {
                val baseFormElement = mFormAdapter.getValueAtIndex(i)
                if (baseFormElement.isRequired && baseFormElement.value == null) {
                    return false
                }
            }
            return true
        }


    val values: Map<String, Any?>
        get() {
            if (!isValidForm) throw IllegalStateException("Invalid Form")

            val result = LinkedHashMap<String, Any?>()
            for (i in 0 until mFormAdapter.itemCount) {
                val baseFormElement = mFormAdapter.getValueAtIndex(i)

                if (baseFormElement.value != null) {
                    result[baseFormElement.id!!] = baseFormElement.value
                }
            }
            return result
        }
}