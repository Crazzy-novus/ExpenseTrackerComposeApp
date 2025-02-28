package com.example.myexpensetracker.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myexpensetracker.R
import com.example.myexpensetracker.models.ExpenseRecord
import com.example.myexpensetracker.screens.HomeScreen
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : ComponentActivity() {

    // Initialize the Shared Preference Instance to get stored data
    private lateinit var sharedPreferences: SharedPreferences

    private var userExpenseRecord = mutableStateListOf<ExpenseRecord>()
    private var categoryList = (arrayListOf ("Food", "Trip", "Petrol", "Party"))

    private var darkTheme by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("ExpenseTrackerPrefs", MODE_PRIVATE)

        loadExpenseRecords() // Load stored expenses on startup

        enableEdgeToEdge()

        setContent {

            // To Icon Corresponding to Theme
            val themeIconId = if (darkTheme) R.drawable.dark_theme_icon_50 else R.drawable.light_theme_icon_50

            // To store the transition value of icon while click the icon
            MyExpenseTrackerTheme(darkTheme = darkTheme)
            {
                HomeScreen(
                    context = this,
                    userExpenseRecordList = userExpenseRecord,
                    categoryList = categoryList,
                    themeIcon = themeIconId,
                    onThemeIconClicked = {
                        darkTheme = !darkTheme
                    }
                )
            }
        }
    }

    override fun onStop() {
        super.onStop()
        saveExpenseRecords()
    }

    // Save the List to saved Preference when activity is closed
    private fun saveExpenseRecords() {
        val editor = sharedPreferences.edit()

        if (userExpenseRecord.isNotEmpty())
        {
            // Initialize json to store list in shared preference
            val gson = Gson()
            val json = gson.toJson(userExpenseRecord)
            editor.putString("userExpenseRecord", json)
        }

        editor.putBoolean("themeState", darkTheme)
        editor.apply()
    }

    // Load the List of expense When activity creates
    private fun loadExpenseRecords()
    {
        val json = sharedPreferences.getString("userExpenseRecord", null) ?: return // Return null if no preference is available

        // If json is not null decode the json string to ExpenseRecordObject
        val gson = Gson()
        val type = object : TypeToken<MutableList<ExpenseRecord>>() {}.type
        val savedList: MutableList<ExpenseRecord> = gson.fromJson(json, type) ?: mutableListOf()

        userExpenseRecord.clear()
        userExpenseRecord.addAll(savedList)
        darkTheme = sharedPreferences.getBoolean("themeState", true) // Default to light mode (false)
    }

}


