package com.arstagaev.calcbalance.fragments

import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.arstagaev.calcbalance.MainActivity
import com.arstagaev.calcbalance.R

class FinishFragment : Fragment(R.layout.fragment_finish) {

    lateinit var buttonCard: CardView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCard = view.findViewById(R.id.finish_screen_generate_excel)


        buttonCard.setOnClickListener {
            (activity as MainActivity).generateExcel()

        }
    }
}