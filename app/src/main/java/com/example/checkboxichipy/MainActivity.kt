package com.example.checkboxichipy

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var vegetablesCheckbox: CheckBox
    private lateinit var meatCheckbox: CheckBox
    private lateinit var breadCheckbox: CheckBox
    private lateinit var chipGroup: ChipGroup
    private lateinit var addButton: Button
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radio_group)
        vegetablesCheckbox = findViewById(R.id.checkbox_vegetables)
        meatCheckbox = findViewById(R.id.checkbox_meat)
        breadCheckbox = findViewById(R.id.checkbox_bread)
        chipGroup = findViewById(R.id.chip_group)
        addButton = findViewById(R.id.add_button)
        clearButton = findViewById(R.id.clear_button)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.add_radio_button -> {
                    vegetablesCheckbox.visibility = View.VISIBLE
                    meatCheckbox.visibility = View.VISIBLE
                    breadCheckbox.visibility = View.VISIBLE
                    chipGroup.visibility = View.GONE
                    clearButton.visibility = View.GONE
                    addButton.visibility = View.VISIBLE
                }
                R.id.clear_radio_button -> {
                    vegetablesCheckbox.visibility = View.GONE
                    meatCheckbox.visibility = View.GONE
                    breadCheckbox.visibility = View.GONE
                    chipGroup.visibility = View.VISIBLE
                    clearButton.visibility = View.VISIBLE
                    addButton.visibility = View.GONE
                }
            }
        }

        addButton.setOnClickListener {
            val selectedItems = mutableListOf<String>()
            if (vegetablesCheckbox.isChecked) {
                selectedItems.add(vegetablesCheckbox.text.toString())
            }
            if (meatCheckbox.isChecked) {
                selectedItems.add(meatCheckbox.text.toString())
            }
            if (breadCheckbox.isChecked) {
                selectedItems.add(breadCheckbox.text.toString())
            }

            selectedItems.forEach {
                val chip = Chip(this)
                chip.text = it
                when (it) {
                    "Tomatoes", "Carrots" -> chip.setChipBackgroundColorResource(R.color.vegetables)
                    "Beef", "Chicken" -> chip.setChipBackgroundColorResource(R.color.meat)
                    "White bread", "Whole wheat bread" -> chip.setChipBackgroundColorResource(R.color.bread)
                }
                chipGroup.addView(chip)
            }
        }

        clearButton.setOnClickListener {
            chipGroup.removeAllViews()
        }
    }
}