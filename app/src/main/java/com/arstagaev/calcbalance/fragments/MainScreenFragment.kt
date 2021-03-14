package com.arstagaev.calcbalance.fragments

import android.accessibilityservice.GestureDescription
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arstagaev.calcbalance.R


class MainScreenFragment : Fragment(R.layout.fragment_main) {

    lateinit var cardviewButtonStart : CardView
    lateinit var helper : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardviewButtonStart = view.findViewById(R.id.card_button_start)

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