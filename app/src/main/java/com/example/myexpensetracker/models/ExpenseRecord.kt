package com.example.myexpensetracker.models


data class ExpenseRecord (
    //private var expenseCategoryId : Int,
    //private var userId : Int,
    var expenseCategory : String = "",
    var amount : Float = 0F,
    var date: String = "",
    var description: String = ""
)