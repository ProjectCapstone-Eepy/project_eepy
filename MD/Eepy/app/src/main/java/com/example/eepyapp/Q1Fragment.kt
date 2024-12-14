package com.example.eepyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Q1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Q1Fragment(private val onAnswer: (Int) -> Unit) : Fragment(R.layout.fragment_q1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ageInput = view.findViewById<EditText>(R.id.input_age)
        val nextButton = view.findViewById<Button>(R.id.next_button)

        nextButton.setOnClickListener{
            val age = ageInput.text.toString().toIntOrNull()
            if (age != null) {
                onAnswer(age)

            } else {
                ageInput.error = "Masukan umur yang valid!"
            }

        }
    }

}