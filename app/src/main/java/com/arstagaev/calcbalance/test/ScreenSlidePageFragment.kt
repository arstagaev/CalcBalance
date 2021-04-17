package com.arstagaev.calcbalance.test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.arstagaev.calcbalance.MainActivity
import com.arstagaev.calcbalance.MainViewModel
import com.arstagaev.calcbalance.R
import com.arstagaev.calcbalance.models.Agreement
import com.arstagaev.calcbalance.models.Requirements

class ScreenSlidePageFragment : Fragment(R.layout.fragment_screen_slide_page) {

    private lateinit var viewModelx: MainViewModel

    private lateinit var nameOfAgreement: EditText
    private lateinit var price: EditText
    private lateinit var percents: EditText
    private lateinit var finishCash: EditText

    private lateinit var dateFrom: DatePicker
    private lateinit var dateTo: DatePicker
    private lateinit var dateFinish: DatePicker

    private lateinit var responsibility: Spinner

///////////////////////////////////////////////
    private lateinit var agreement : Agreement
    private var name             : String =""

    private var firstToSecond    : Boolean = true
    private var requmpetns       : ArrayList<Requirements> = arrayListOf()

    private var priceX           : Float = 0F
    private var dateStartX       : String = ""
    private var dateFinishX      : String = ""
    private var percentsX        : Float = 0F
    private var responsobilityX  : String = ""
    private var dateDoneX        : String = ""
    private var sumDoneX         : Float = 0F

    private var          priceY : Float = 0F
    private var      dateStartY : String = ""
    private var     dateFinishY : String = ""
    private var       percentsY : Float = 0F
    private var responsobilityY : String = ""
    private var       dateDoneY : String = ""
    private var        sumDoneY : Float = 0F

    private var          priceZ : Float = 0F
    private var      dateStartZ : String = ""
    private var     dateFinishZ : String = ""
    private var       percentsZ : Float = 0F
    private var responsobilityZ : String = ""
    private var       dateDoneZ : String = ""
    private var        sumDoneZ : Float = 0F



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameOfAgreement = view.findViewById(R.id.name_of_agreement)
        price           = view.findViewById(R.id.price_input)
        percents        = view.findViewById(R.id.percents)
        finishCash      = view.findViewById(R.id.cash_finish_exe)

        viewModelx = (activity as MainActivity).viewModel



        changeEditTextListeners()


    }

    private fun changeEditTextListeners() {
        //name = nameOfAgreement.text.toString()
        nameOfAgreement.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                //viewModelx.businessX.value!![0].name = s.toString()
               name = s.toString()
               viewModelx.addNameOfAgreement(s.toString(),0)

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        price.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        percents.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        finishCash.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun onPause() {
        saveAllInputs()
        Log.d("ccc","size "+viewModelx.businessX.value!!.size)
        super.onPause()
    }

    private fun saveAllInputs() {
        name
        firstToSecond
        requmpetns



        priceX
        dateStartX
        dateFinishX
        percentsX
        responsobilityX
        dateDoneX
        sumDoneX

        requmpetns.add(
            Requirements(
                priceX
                ,dateStartX
                ,dateFinishX
                ,percentsX
                ,responsobilityX
                ,dateDoneX,
                sumDoneX))

        agreement = Agreement(name,firstToSecond,requmpetns)

        viewModelx.businessX.value!!.add(agreement)




    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("ccc","on act created")
        //viewModelx = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}