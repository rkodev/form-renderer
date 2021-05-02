package com.roksky.form_renderer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.roksky.form_renderer.R;
import com.roksky.form_renderer.listener.FormItemEditTextListener;
import com.roksky.form_renderer.listener.OnFormElementValueChangedListener;
import com.roksky.form_renderer.listener.ReloadListener;
import com.roksky.form_renderer.model.BaseFormElement;
import com.roksky.form_renderer.viewholder.BaseViewHolder;
import com.roksky.form_renderer.viewholder.FormElementHeader;
import com.roksky.form_renderer.viewholder.FormElementLocationPickerViewHolder;
import com.roksky.form_renderer.viewholder.FormElementPickerDateViewHolder;
import com.roksky.form_renderer.viewholder.FormElementPickerMultiViewHolder;
import com.roksky.form_renderer.viewholder.FormElementPickerSingleViewHolder;
import com.roksky.form_renderer.viewholder.FormElementPickerTimeViewHolder;
import com.roksky.form_renderer.viewholder.FormElementRadioViewHolder;
import com.roksky.form_renderer.viewholder.FormElementSwitchViewHolder;
import com.roksky.form_renderer.viewholder.FormElementTextEmailViewHolder;
import com.roksky.form_renderer.viewholder.FormElementTextMultiLineViewHolder;
import com.roksky.form_renderer.viewholder.FormElementTextNumberViewHolder;
import com.roksky.form_renderer.viewholder.FormElementTextPasswordViewHolder;
import com.roksky.form_renderer.viewholder.FormElementTextPhoneViewHolder;
import com.roksky.form_renderer.viewholder.FormElementTextSingleLineViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter the holds and displays the form objects
 * Created by Adib on 16-Apr-17.
 */

public class FormAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ReloadListener {

    private final Activity mActivity;
    private final OnFormElementValueChangedListener mListener;
    private List<BaseFormElement> mDataset;

    /**
     * public constructor with context
     *
     * @param activity
     */
    public FormAdapter(Activity activity, OnFormElementValueChangedListener listener) {
        mActivity = activity;
        mListener = listener;
        mDataset = new ArrayList<>();
    }

    public Integer getItemPosition(BaseFormElement element){
        return mDataset.indexOf(element);
    }

    /**
     * adds list of elements to be shown
     *
     * @param formObjects
     */
    public void addElements(List<BaseFormElement> formObjects) {
        this.mDataset = formObjects;
        notifyDataSetChanged();
    }

    /**
     * adds single element to be shown
     *
     * @param formObject
     */
    public void addElement(BaseFormElement formObject) {
        this.mDataset.add(formObject);
        notifyDataSetChanged();
    }

    /**
     * set value for any unique index
     *
     * @param position
     * @param value
     */
    public void setValueAtIndex(int position, String value) {
        BaseFormElement baseFormElement = mDataset.get(position);
        baseFormElement.setValue(value);
        notifyDataSetChanged();
    }

    /**
     * set value for any unique tag
     *
     * @param tag
     * @param value
     */
    public void setValueAtTag(int tag, String value) {
        for (BaseFormElement f : this.mDataset) {
            if (f.getTag() == tag) {
                f.setValue(value);
                return;
            }
        }
        notifyDataSetChanged();
    }

    /**
     * get value of any element by tag
     *
     * @param index
     * @return
     */
    public BaseFormElement getValueAtIndex(int index) {
        return (mDataset.get(index));
    }

    /**
     * get value of any element by tag
     *
     * @param tag
     * @return
     */
    public BaseFormElement getValueAtTag(int tag) {
        for (BaseFormElement f : this.mDataset) {
            if (f.getTag() == tag) {
                return f;
            }
        }

        return null;
    }

    /**
     * get value of any element by id
     *
     * @param id
     * @return
     */
    public BaseFormElement getValueAtId(String id) {
        for (BaseFormElement f : this.mDataset) {
            if (f.getId() != null && f.getId().equals(id)) {
                return f;
            }
        }

        return null;
    }

    /**
     * get whole dataset
     *
     * @return
     */
    public List<BaseFormElement> getDataset() {
        return mDataset;
    }

    /**
     * get value changed listener
     *
     * @return
     */
    public OnFormElementValueChangedListener getValueChangeListener() {
        return mListener;
    }

    /**
     * gets total item count
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * gets view item type based on header, or the form element type
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).getType();
    }

    /**
     * creating the view holder to be shown for a position
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // get layout based on header or element type
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        switch (viewType) {
            case BaseFormElement.TYPE_HEADER:
                v = inflater.inflate(R.layout.form_element_header, parent, false);
                return new FormElementHeader(v);
            case BaseFormElement.TYPE_EDITTEXT_TEXT_SINGLELINE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextSingleLineViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_TEXT_MULTILINE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextMultiLineViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_NUMBER:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextNumberViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_EMAIL:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextEmailViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_PHONE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextPhoneViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_PASSWORD:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextPasswordViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_PICKER_DATE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerDateViewHolder(v, mActivity, this);
            case BaseFormElement.TYPE_PICKER_TIME:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerTimeViewHolder(v, mActivity, this);
            case BaseFormElement.TYPE_PICKER_SINGLE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerSingleViewHolder(v, mActivity, this);
            case BaseFormElement.TYPE_PICKER_MULTI:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerMultiViewHolder(v, mActivity, this);
            case BaseFormElement.TYPE_SWITCH:
                v = inflater.inflate(R.layout.form_element_switch, parent, false);
                return new FormElementSwitchViewHolder(v, mActivity, this);
            case BaseFormElement.TYPE_LOCATION:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementLocationPickerViewHolder(v, mActivity, this);
            case BaseFormElement.TYPE_RADIO:
                v = inflater.inflate(R.layout.form_element_radio, parent, false);
                return new FormElementRadioViewHolder(v, mActivity, this);
            default:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextSingleLineViewHolder(v, new FormItemEditTextListener(this));
        }
    }

    /**
     * draws the view for the position specific view holder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        // updates edit text listener index
        if (holder.getListener() != null) {
            holder.getListener().updatePosition(holder.getAdapterPosition());
        }

        // gets current object
        BaseFormElement currentObject = mDataset.get(position);
        holder.bind(position, currentObject, mActivity);
    }

    /**
     * use the listener to update value and notify dataset changes to adapter
     *
     * @param position
     * @param updatedValue
     */
    @Override
    public <T> void updateValue(int position, T updatedValue) {
        mDataset.get(position).setValue(updatedValue);
        notifyDataSetChanged();
        if (mListener != null)
            mListener.onValueChanged(mDataset.get(position));
    }

}