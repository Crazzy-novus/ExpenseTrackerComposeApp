package com.example.myexpensetracker.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myexpensetracker.theme.MyExpenseTrackerTheme
import com.example.myexpensetracker.screens.ExpenseRecordCreationScreen

class CreateExpenseActivity : ComponentActivity() {
    var recordId : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = intent
        recordId = intent.getStringExtra("recordId")?.toInt()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyExpenseTrackerTheme {
               ExpenseRecordCreationScreen(recordId)
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