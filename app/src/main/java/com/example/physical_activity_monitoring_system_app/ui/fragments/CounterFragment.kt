package com.example.physical_activity_monitoring_system_app.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.physical_activity_monitoring_system_app.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_counter.*
import kotlinx.android.synthetic.main.fragment_setup.*

class CounterFragment: Fragment(R.layout.fragment_counter) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cbPpm.isChecked = true
        buttonCalculateCpm.isEnabled = false
        buttonCalculateCpm.isClickable = false

        cbPpm.setOnClickListener {
            cbPpmClicked()
            tvCalculatedScore.text = ""
        }

        cbCpm.setOnClickListener {
            cbCpmClicked()
            tvCalculatedScore.text = ""
        }

        buttonCalculatePpm.setOnClickListener {
            calculatePpm()
        }

        buttonCalculateCpm.setOnClickListener {
            calculateCpm()
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sex,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSex.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.activity,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerActivity.adapter = adapter
        }
    }

    private fun cbPpmClicked() {
        if(cbPpm.isChecked){
            if(cbCpm.isChecked){
                cbCpm.isChecked = false
            }
            tvPpmCpm.setText("Basic Metabolism Calculator")
            buttonCalculateCpm.isEnabled = false
            buttonCalculateCpm.isClickable = false
            buttonCalculateCpm.visibility = GONE
            buttonCalculatePpm.isEnabled = true
            buttonCalculatePpm.isClickable = true
            buttonCalculatePpm.visibility = VISIBLE
            tvActivity.isEnabled = false
            tvActivity.isClickable = false
            tvActivity.visibility = GONE
            spinnerActivity.isEnabled = false
            spinnerActivity.isClickable = false
            spinnerActivity.visibility = GONE
        }
    }

    private fun cbCpmClicked() {
        if(cbCpm.isChecked){
            if(cbPpm.isChecked){
                cbPpm.isChecked = false
            }
            tvPpmCpm.setText("Total Metabolism Calculator")
            buttonCalculateCpm.isEnabled = true
            buttonCalculateCpm.isClickable = true
            buttonCalculateCpm.visibility = VISIBLE
            buttonCalculatePpm.isEnabled = false
            buttonCalculatePpm.isClickable = false
            buttonCalculatePpm.visibility = GONE
            tvActivity.isEnabled = true
            tvActivity.isClickable = true
            tvActivity.visibility = VISIBLE
            spinnerActivity.isEnabled = true
            spinnerActivity.isClickable = true
            spinnerActivity.visibility = VISIBLE
        }
    }

    private fun calculatePpm() {
        var sex: String
        var ageString: String
        var heightString: String
        var weightString: String
        var age: Double
        var height: Double
        var weight: Double
        var result: Double
        if(spinnerSex != null && spinnerSex.selectedItem != null
            && etAge.text.toString().trim().length > 0 && TextUtils.isDigitsOnly(etAge.text.toString()) == true
            && etHeight.text.toString().trim().length > 0 && TextUtils.isDigitsOnly(etHeight.text.toString()) == true
            && etWeightCalculator.text.toString().trim().length > 0 && TextUtils.isDigitsOnly(etWeightCalculator.text.toString()) == true) {
                sex = spinnerSex.selectedItem.toString()
                ageString = etAge.text.toString()
                heightString = etHeight.text.toString()
                weightString = etWeightCalculator.text.toString()
                age = ageString.toDouble()
                height = heightString.toDouble()
                weight = weightString.toDouble()
                if(sex.equals("man")){
                    result = (10 * weight) + (6.25 * height) - (5 * age) + 5;
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(sex.equals("woman")) {
                    result = (10 * weight) + (6.25 * height) - (5 * age) - 161;
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else {
                    Snackbar.make(requireView(), "To calculcate, you need to choose your sex.", Snackbar.LENGTH_SHORT).show()
                }
        } else {
            Snackbar.make(requireView(), "Please, fill in all fields.", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun calculateCpm() {
        var sex: String
        var activity: String
        var ageString: String
        var heightString: String
        var weightString: String
        var age: Double
        var height: Double
        var weight: Double
        var result: Double
        if(spinnerSex != null && spinnerSex.selectedItem != null
            && spinnerActivity != null && spinnerActivity.selectedItem != null
            && etAge.text.toString().trim().length > 0 && TextUtils.isDigitsOnly(etAge.text.toString()) == true
            && etHeight.text.toString().trim().length > 0 && TextUtils.isDigitsOnly(etHeight.text.toString()) == true
            && etWeightCalculator.text.toString().trim().length > 0 && TextUtils.isDigitsOnly(etWeightCalculator.text.toString()) == true) {
            sex = spinnerSex.selectedItem.toString()
            activity = spinnerActivity.selectedItem.toString()
            ageString = etAge.text.toString()
            heightString = etHeight.text.toString()
            weightString = etWeightCalculator.text.toString()
            age = ageString.toDouble()
            height = heightString.toDouble()
            weight = weightString.toDouble()
            if(sex.equals("man")){
                result = (10 * weight) + (6.25 * height) - (5 * age) + 5;
                if(activity.equals("lack")) {
                    result *= 1.2
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("low")) {
                    result *= 1.25
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("moderate")) {
                    result *= 1.5
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("high")) {
                    result *= 1.75
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("very high")) {
                    result *= 2.0
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else {
                    Snackbar.make(requireView(), "To calculcate, you need to choose your activity.", Snackbar.LENGTH_SHORT).show()
                }
            } else if(sex.equals("woman")) {
                result = (10 * weight) + (6.25 * height) - (5 * age) - 161;
                if(activity.equals("lack")) {
                    result *= 1.2
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("low")) {
                    result *= 1.25
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("moderate")) {
                    result *= 1.5
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("high")) {
                    result *= 1.75
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else if(activity.equals("very high")) {
                    result *= 2.0
                    tvCalculatedScore.visibility = VISIBLE
                    tvCalculatedScore.text = "Your basic calorie metabolism is: ${result}"
                } else {
                    Snackbar.make(requireView(), "To calculcate, you need to choose your activity.", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(requireView(), "To calculcate, you need to choose your sex.", Snackbar.LENGTH_SHORT).show()
            }
        } else {
            Snackbar.make(requireView(), "Please, fill in all fields.", Snackbar.LENGTH_SHORT).show()
        }
    }
}