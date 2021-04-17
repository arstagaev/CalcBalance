package com.arstagaev.calcbalance.models

data class Business(
    val agreements : List<Agreement>
)

data class Agreement(
    var name : String,
    var firstToSecond : Boolean, // кто кому
    var requmpetns : ArrayList<Requirements>
)

data class Requirements(
    var price : Float,
    var dateStart : String,
    var dateFinish : String,
    var percents : Float,
    var responsobility : String,
    var dateDone : String,
    var sumDone : Float

)