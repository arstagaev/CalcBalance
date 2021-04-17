package com.arstagaev.calcbalance.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arstagaev.calcbalance.MainViewModel
import com.arstagaev.calcbalance.R

class ScreenSlidePageFragment2 : Fragment(R.layout.fragment_screen_slide_page2) {

    private lateinit var viewModelx: MainViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}