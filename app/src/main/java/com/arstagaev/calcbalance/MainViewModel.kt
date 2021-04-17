package com.arstagaev.calcbalance

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arstagaev.calcbalance.models.Agreement

class MainViewModel : ViewModel() {

    var asd = MutableLiveData<String>()
    var businessX = MutableLiveData<ArrayList<Agreement>>()
    var processMember1 = MutableLiveData<String>()
    var processMember2 = MutableLiveData<String>()

    @SuppressLint("LogNotTimber")
    fun addNameOfAgreement(str : String, numberOfAgreement : Int){
        businessX.value!!.get(0).name = str
        Log.d("ccc"," is "+businessX.value!!.get(0).name)

    }

    fun setProcessMembers(member1 : String,member2 : String){
        processMember1.value = member1
        processMember2.value = member2

    }
}