package com.example.myexpensetracker.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ExpenseRecord (
    //private var expenseCategoryId : Int,
    val recordId : Int = GenerateId.generateUserId(),
    var expenseCategory : String = "",
    var amount : Float = 0F,
    var date: String = "",
    var description: String = ""
) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )
     object GenerateId{
        private var idGenerator : Int = 0
        fun generateUserId(): Int
        {
            return ++idGenerator
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(recordId)
        parcel.writeString(expenseCategory)
        parcel.writeFloat(amount)
        parcel.writeString(date)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<ExpenseRecord> {
        override fun createFromParcel(parcel: Parcel): ExpenseRecord {
            return ExpenseRecord(parcel)
        }

        override fun newArray(size: Int): Array<ExpenseRecord?> {
            return arrayOfNulls(size)
        }
    }
}
