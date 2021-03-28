package com.arstagaev.calcbalance.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arstagaev.calcbalance.R

class SecondFragment : Fragment(R.layout.fragment_second) {

    lateinit var buttonDate : DatePicker
    lateinit var cardviewButtonNext : CardView
    val arraySpin = arrayOf("Юр Лицо","Физ Лицо")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonDate= view.findViewById(R.id.date_picker_actions)
        cardviewButtonNext = view.findViewById(R.id.finish_screen_generate_excel)

        cardviewButtonNext.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_finishFragment)
        }

        val spinner: Spinner = view.findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    private fun openDatePicker() {

    }
}