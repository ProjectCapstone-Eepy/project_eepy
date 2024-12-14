package com.example.eepyapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class Q5Fragment(private val onAnswer: (Int) -> Unit) : Fragment(R.layout.fragment_q5) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputStress = view.findViewById<EditText>(R.id.input_stress)
        val nextButton = view.findViewById<Button>(R.id.next_button_stress)

        nextButton.setOnClickListener {
            val stressValue = inputStress.text.toString().toIntOrNull()
            if (stressValue != null && stressValue in 1..10) {
                onAnswer(stressValue)
            } else {
                inputStress.error = "Input harus di antara 1-10"
            }
        }
    }
}
