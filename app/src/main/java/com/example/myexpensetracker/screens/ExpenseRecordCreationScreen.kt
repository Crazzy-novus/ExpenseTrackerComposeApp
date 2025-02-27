package com.example.myexpensetracker.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.R
import com.example.myexpensetracker.activity.MainActivity
import com.example.myexpensetracker.components.AppTopToolBar
import com.example.myexpensetracker.components.buttons.CustomButton
import com.example.myexpensetracker.components.textField.CustomTextField
import com.example.myexpensetracker.components.DatePickerModal
import com.example.myexpensetracker.components.ListBottomList
import com.example.myexpensetracker.models.ExpenseRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ExpenseRecordCreationScreen(recordId : Int?)
{
    // Create Record Object to store Details
    var record = ExpenseRecord()

    // Get the current date in milliseconds
    val currentDateMillis = System.currentTimeMillis()
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currentDateString = sdf.format(Date(currentDateMillis))
    record.date = currentDateString
    var amountString = "" // this variable is used to store empty string for amount
    if (recordId != null)
    {
        record = MainActivity.ExpenseRecordData.userExpenseRecord[recordId].copy() // TODO Need to pass reference
        amountString = record.amount.toString()
    }

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    //TODO (Add validation)
    var showModal by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(record.date) }
    var amount by remember { mutableStateOf(amountString) }
    var category by remember { mutableStateOf(record.expenseCategory) }
    var description by remember { mutableStateOf(record.description) }

    Scaffold (

        modifier = Modifier
            .fillMaxSize(),

        topBar = {

            AppTopToolBar(
                title = stringResource(R.string.Expense_Tracker),
                navigationIconId = R.drawable.arrow_back,
                navigationIconAction = {
                    backDispatcher?.onBackPressed()
                },
                onClickAction = {  },
            )
        }
    )
    {innerPadding ->

        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
        )
        {

            CustomTextField(
                textValue = date,
                onTextChanged = {
                },
                label = stringResource(R.string.enter_Date),
                placeHolder = stringResource(R.string.date),
                readOnly = true,
                trailingIcon = R.drawable.date_icon,
                onClick = {
                    showModal = true
                }
            )
            if (showModal)
            {
                DatePickerModal(
                    onDateSelected = {
                        if (it != null)
                        {
                            val temDate = Date(it)
                            date = sdf.format(temDate)
                        }
                    },
                    onDismiss = { showModal = false }
                )
            }

            CustomTextField(
                textValue = amount,
                onTextChanged = {
                    if (it.isEmpty() || it.toDoubleOrNull() != null)
                    {
                        amount = it
                    }
                    //TODO : Add invalidation notification
                },
                label = stringResource(R.string.enter_amount),
                placeHolder = stringResource(R.string.amount)
            )

            CustomTextField(
                textValue = category,
                onTextChanged = {
                    category = it
                },
                label = stringResource(R.string.enter_category),
                placeHolder = stringResource(R.string.category),
                trailingIcon = R.drawable.down_arrow,
                onClick = {showBottomSheet = true}
            )

//            if (showBottomSheet)
//            {
//                ListBottomList (
//                    onDismissRequest = {
//                        showBottomSheet = false
//                    }
//                )
//            }

            CustomTextField(
                textValue = description,
                onTextChanged = {
                    description = it
                },
                label = stringResource(R.string.enter_description),
                placeHolder = stringResource(R.string.description)
            )

            CustomButton(
                text = stringResource(R.string.save),
                onClickAction = {
                    saveExpenseRecord(
                        date = date,
                        amount = amount.toFloat(),
                        category = category,
                        description = description,
                        record = record,
                        recordId = recordId
                        )
                    backDispatcher?.onBackPressed()
                }
            )
        }
    }
}

fun saveExpenseRecord(date: String, amount: Float, category : String, description : String, record: ExpenseRecord, recordId: Int? = null)
{
    record.date = date
    record.amount = amount
    record.expenseCategory = category
    record.description = description
    if (recordId != null)
    {
        MainActivity.ExpenseRecordData.userExpenseRecord[recordId] = record
    }
    else
    {
        MainActivity.ExpenseRecordData.userExpenseRecord.add(record)
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayMode1()
{
//    TextView("Amount")
   ExpenseRecordCreationScreen(0)
}