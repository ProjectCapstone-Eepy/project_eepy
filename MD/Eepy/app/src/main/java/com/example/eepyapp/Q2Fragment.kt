package com.example.eepyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class Q2Fragment(private val onAnswer: (String) -> Unit) : Fragment(R.layout.fragment_q2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val maleButton = view.findViewById<Button>(R.id.answer1)
        val femaleButton = view.findViewById<Button>(R.id.answer2)

        maleButton.setOnClickListener{
            onAnswer("Laki-Laki")
        }

        femaleButton.setOnClickListener{
            onAnswer("Perempuan")
        }
    }


}