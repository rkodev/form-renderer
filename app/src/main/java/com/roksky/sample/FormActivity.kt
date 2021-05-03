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
                ElementPickerSingle("select_area").apply {
                    title = "Select Area"
                    hint = "Select Area"
                    options = listOf(
                        ClientArea(
                            name = "East Africa",
                            id = "East Africa"
                        ),
                        ClientArea(
                            name = "West Africa",
                            id = "West Africa"
                        ),
                        ClientArea(
                            name = "North Africa",
                            id = "North Africa"
                        ),
                        ClientArea(
                            name = "South Africa",
                            id = "South Africa"
                        )
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
                ElementLocationPicker("select_location").apply {
                    title = "Select Location"
                    hint = "Customer Location"
                },
                ElementTextSingleLine("select_bags").apply {
                    title = "Bags to issue"
                    hint = "Bags to issue"
                    isRequired = true
                },
                ElementRadio("select_bags_type").apply {
                    title = "Bags Type Issued"
                    hint = "Bags Type Issued"
                    options = composeOptions(listOf("Large", "Small"))
                    isRequired = true
                },
                ElementTextNumber("occupied_units").apply {
                    title = "Number of occupied Units"
                    hint = "Number of occupied Units"
                    isRequired = true
                },
                ElementTextNumber("issued_bags").apply {
                    title = "Number of issued bags"
                    hint = "Number of issued bags"
                    isRequired = true
                    readOnly = true
                },
            )
        )
    }
}