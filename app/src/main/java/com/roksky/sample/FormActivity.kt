package com.roksky.sample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.roksky.form_renderer.FormBuilder
import com.roksky.form_renderer.model.*

class FormActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var formBuilder: FormBuilder
    private lateinit var progressBarLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        mRecyclerView = findViewById(R.id.recyclerView)

        progressBarLoading = findViewById(R.id.progressBarLoading)
        progressBarLoading.visibility = View.GONE

        formBuilder = FormBuilder(this@FormActivity, mRecyclerView)
        formBuilder.addFormElements(
            listOf(
                FormElementPickerSingle.createInstance().apply {
                    id = "select_area"
                    title = "Select Area"
                    hint = "Select Area"
                    options = listOf(
                        ClientArea().apply {
                            name = "East Africa"
                            id = "East Africa"
                        },
                        ClientArea().apply {
                            name = "West Africa"
                            id = "West Africa"
                        },
                        ClientArea().apply {
                            name = "North Africa"
                            id = "North Africa"
                        },
                        ClientArea().apply {
                            name = "South Africa"
                            id = "South Africa"
                        }
                    ).map { area ->
                        object : ElementValue<ClientArea> {
                            override fun toDisplayValue(): String {
                                return area.name!!
                            }

                            override fun value(): ClientArea {
                                return area
                            }

                        }
                    }
                    isRequired = true
                },
                FormElementLocationPicker.createInstance().apply {
                    id = "select_location"
                    title = "Select Location"
                    hint = "Customer Location"
                },
                FormElementTextSingleLine.createInstance().apply {
                    id = "select_bags"
                    title = "Bags to issue"
                    hint = "Bags to issue"
                    isRequired = true
                },
                FormElementRadio.createInstance().apply {
                    id = "select_bags_type"
                    title = "Bags Type Issued"
                    hint = "Bags Type Issued"
                    options = composeOptions(listOf("Large", "Small"))
                    isRequired = true
                },
                FormElementTextNumber.createInstance().apply {
                    id = "occupied_units"
                    title = "Number of occupied Units"
                    hint = "Number of occupied Units"
                    isRequired = true
                },
                FormElementTextNumber.createInstance().apply {
                    id = "issued_bags"
                    title = "Number of issued bags"
                    hint = "Number of issued bags"
                    isRequired = true
                    readOnly = true
                },
            )
        )
    }
}