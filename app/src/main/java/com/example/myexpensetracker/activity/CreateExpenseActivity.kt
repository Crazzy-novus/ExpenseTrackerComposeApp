package com.example.myexpensetracker.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myexpensetracker.R
import com.example.myexpensetracker.models.ExpenseRecord
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme
import com.example.myexpensetracker.screens.ExpenseRecordCreationScreen

class CreateExpenseActivity : ComponentActivity()
{

    private lateinit var expenseRecord : ExpenseRecord
    private lateinit var categoryList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
  //TODO need to find other way

        categoryList = intent.getStringArrayListExtra(R.string.category_list_key.toString())?: arrayListOf("Default Category")

        expenseRecord = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {

            intent.getParcelableExtra(R.string.expense_record_key.toString(), ExpenseRecord::class.java)
        }
        else
        {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("recordObject")
        } ?: ExpenseRecord()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MyExpenseTrackerTheme {
               ExpenseRecordCreationScreen(
                   activity = this,
                   record = expenseRecord,
                   categoryList = categoryList)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier)
{
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2()
{
    MyExpenseTrackerTheme()
    {
        Greeting("Android")
    }
}