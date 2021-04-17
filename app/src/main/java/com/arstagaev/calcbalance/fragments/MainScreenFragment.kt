package com.arstagaev.calcbalance.fragments

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arstagaev.calcbalance.MainActivity
import com.arstagaev.calcbalance.MainViewModel
import com.arstagaev.calcbalance.R
import com.arstagaev.calcbalance.models.Agreement
import com.arstagaev.calcbalance.models.Requirements


class MainScreenFragment : Fragment(R.layout.fragment_main) {

    lateinit var cardviewButtonStart : CardView
    lateinit var helper : ImageView
    lateinit var viewModelX: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardviewButtonStart = view.findViewById(R.id.card_button_start)

        viewModelX = (activity as MainActivity).viewModel

        var primer = arrayListOf<Agreement>()
        var reqip = arrayListOf<Requirements>()
        reqip.add(Requirements(0F,"01.01.1970","01.01.1971",0F,"resp","01.01.1971",0F))
        reqip.add(Requirements(0F,"01.01.1970","01.01.1971",0F,"resp","01.01.1971",0F))
        reqip.add(Requirements(0F,"01.01.1970","01.01.1971",0F,"resp","01.01.1971",0F))

        primer.add(Agreement("name",true,reqip))
        primer.add(Agreement("name",true,reqip))
        primer.add(Agreement("name",true,reqip))

        viewModelX.businessX.value = primer

        cardviewButtonStart.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        helper = view.findViewById(R.id.helper)

        helper.setOnClickListener {
            showDialogAboutUs()
        }
    }

    private fun showDialogAboutUs(){

        var version = ""
        try {
            val pInfo = requireActivity().packageManager?.getPackageInfo(requireActivity().packageName, 0)
            version = pInfo?.versionName.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        val alertDialog: android.app.AlertDialog = android.app.AlertDialog.Builder(activity).create()
        alertDialog.setTitle("Подсказка")
        alertDialog.setMessage("Привет")
        alertDialog.setButton(
            android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        alertDialog.show()
    }
}