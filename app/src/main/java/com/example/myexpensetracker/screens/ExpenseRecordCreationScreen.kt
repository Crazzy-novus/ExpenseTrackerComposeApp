package com.example.myexpensetracker.screens

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import com.example.myexpensetracker.R
import com.example.myexpensetracker.components.AppTopToolBar
import com.example.myexpensetracker.components.buttons.CustomButton
import com.example.myexpensetracker.components.textField.CustomTextField
import com.example.myexpensetracker.components.DatePickerModal
import com.example.myexpensetracker.components.BottomSheetList
import com.example.myexpensetracker.models.ExpenseRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ExpenseRecordCreationScreen(
    activity: ComponentActivity,
    record : ExpenseRecord,
    categoryList : ArrayList<String>
)
{

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Create Empty Record Object to store Record Details
    var amountString = "" // this variable is used to store empty string for amount

    if (record.date != "")
    {
        amountString = record.amount.toString()
    }
    else
    {
        // Get the current date in milliseconds
        val currentDateMillis = System.currentTimeMillis()
        // Format the date to dd/MM/yyy format
        val currentDateString = simpleDateFormat.format(Date(currentDateMillis))
        // Store the current date to record object to set default date
        record.date = currentDateString

    }

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var showDatePickerDialog by remember { mutableStateOf(false) } //
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
                    showDatePickerDialog = true
                },
                enabled = false
            )

            if (showDatePickerDialog)
            {
                DatePickerModal(
                    onDateSelected = {
                        if (it != null)
                        {
                            val temDate = Date(it)
                            date = simpleDateFormat.format(temDate)
                        }
                    },
                    onDismiss = { showDatePickerDialog = false }
                )
            }
            var isError by remember { mutableStateOf(false) }
            CustomTextField(

                textValue = amount,
                onTextChanged = {
                    // TODO Try to reduce the number of if condition
                    if (it.isEmpty() || it.toDoubleOrNull() != null)
                    {
                        amount = it
                        isError = false
                    }
                    else
                    {
                        amount = it
                        isError = true
                    }
                },
                label = stringResource(R.string.enter_amount),
                placeHolder = stringResource(R.string.amount),
                isError = isError,
                supportText = stringResource(R.string.invalid_amount),
                keyboardType = KeyboardType.Number
            )

            CustomTextField(
                textValue = category,
                onTextChanged = {
                    category = it
                },
                label = stringResource(R.string.enter_category),
                placeHolder = stringResource(R.string.category),
                trailingIcon = R.drawable.down_arrow,
                enabled = false,
                onClick = {showBottomSheet = true}
            )

            if (showBottomSheet)
            {
                BottomSheetList (
                    expenseCategoryList = categoryList,
                    onItemSelected = { selectedCategory ->
                        category = selectedCategory
                        showBottomSheet = false
                    },
                    onDismissRequest = {
                        showBottomSheet = false
                    }
                )
            }

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
                    if (amount.toDoubleOrNull() != null)
                    {
                        saveExpenseRecord(
                            activity = activity,
                            date = date,
                            amount = amount.toFloat(),
                            category = category,
                            description = description,
                            record = record
                        )
                        backDispatcher?.onBackPressed()
                    }
                    else {
                        isError = true
                    }
                }
            )
        }
    }
}

fun saveExpenseRecord(activity: ComponentActivity, date: String, amount: Float, category : String, description : String, record: ExpenseRecord)
{
    record.date = date
    record.amount = amount
    record.expenseCategory = category
    record.description = description
    val resultIntent = Intent().apply {
        putExtra("recordObject", record)
    }
    activity.setResult(Activity.RESULT_OK, resultIntent)
    activity.finish()
}

@Preview(showBackground = true)
@Composable
fun DisplayMode1()
{
//    TextView("Amount")
//   ExpenseRecordCreationScreen(0)
}