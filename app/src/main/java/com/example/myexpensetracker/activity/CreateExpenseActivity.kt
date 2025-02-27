package com.example.myexpensetracker.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme
import com.example.myexpensetracker.screens.ExpenseRecordCreationScreen

class CreateExpenseActivity : ComponentActivity() {

    private var recordIndex : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Get the record Id from the intent to get particular record from list
        recordIndex = intent.getStringExtra("recordIndex")?.toInt()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MyExpenseTrackerTheme {
               ExpenseRecordCreationScreen(recordIndex)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyExpenseTrackerTheme {
        Greeting("Android")
    }
}