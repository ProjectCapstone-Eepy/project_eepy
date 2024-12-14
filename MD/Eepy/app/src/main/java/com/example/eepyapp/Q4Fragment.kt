package com.example.eepyapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class Q4Fragment(private val onAnswer: (Int) -> Unit) : Fragment(R.layout.fragment_q4) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputPhysical = view.findViewById<EditText>(R.id.input_physical)
        val nextButton = view.findViewById<Button>(R.id.next_button_physical)

        nextButton.setOnClickListener {
            val physicalValue = inputPhysical.text.toString().toIntOrNull()
            if (physicalValue != null && physicalValue in 1..10) {
                onAnswer(physicalValue)
            } else {
                inputPhysical.error = "Input harus di antara 1-10"
            }
        }
    }
}
