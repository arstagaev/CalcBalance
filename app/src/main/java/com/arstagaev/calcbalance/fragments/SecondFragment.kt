package com.arstagaev.calcbalance.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arstagaev.calcbalance.MainActivity
import com.arstagaev.calcbalance.MainViewModel
import com.arstagaev.calcbalance.R

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var buttonDate : DatePicker
    private lateinit var cardviewButtonNext : CardView
    val arraySpin = arrayOf("Юр Лицо","Физ Лицо")

    private lateinit var member1 : EditText
    private lateinit var member2 : EditText
    private lateinit var viewmodelX : MainViewModel

    private var agent1 : String = ""
    private var agent2 : String = ""



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //buttonDate= view.findViewById(R.id.date_picker_actions)
        viewmodelX = (activity as MainActivity).viewModel
        cardviewButtonNext = view.findViewById(R.id.finish_screen_generate_excel)

        cardviewButtonNext.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        member1 = view.findViewById(R.id.agent1)
        member2 = view.findViewById(R.id.agent2)

        member1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                agent1 = s.toString()

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        member2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                agent2 = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        //val spinner: Spinner = view.findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            requireActivity(),
//            R.array.planets_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }

    }

    override fun onPause() {
        saveAllData()
        super.onPause()
    }

    private fun saveAllData(){
        viewmodelX.setProcessMembers(agent1,agent2)
    }

    private fun openDatePicker() {

    }
}