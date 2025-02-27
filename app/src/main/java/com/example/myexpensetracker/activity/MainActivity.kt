package com.example.myexpensetracker.activity

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myexpensetracker.R
import com.example.myexpensetracker.models.ExpenseRecord
import com.example.myexpensetracker.screens.HomeScreen
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    // List initialization
    object ExpenseRecordData {
        var userExpenseRecord = mutableStateListOf<ExpenseRecord>()
    }

    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("ExpenseTrackerPrefs", MODE_PRIVATE)
        loadExpenseRecords() // Load stored expenses on startup


        enableEdgeToEdge()

        setContent {

            var darkTheme by remember { mutableStateOf(loadThemeState()) } // To store system Theme false - light/ true - dark
            // To Icon Corresponding to Theme
            val themeIconId = if (darkTheme) R.drawable.dark_theme_icon_50 else R.drawable.light_theme_icon_50
            // To store the transition value of icon while click the icon
            MyExpenseTrackerTheme(darkTheme = darkTheme)
            {
                HomeScreen(
                    context = this,
                    themeIcon = themeIconId,
                    onThemeIconClicked = {
                        darkTheme = !darkTheme
                        saveThemeState(isDarkMode = darkTheme)
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
        val gson = Gson()
        val json = gson.toJson(ExpenseRecordData.userExpenseRecord)
        editor.putString("userExpenseRecord", json)
        editor.apply()
    }

    // Load the List of expense When activity creates
    private fun loadExpenseRecords() {
        val gson = Gson()
        val json = sharedPreferences.getString("userExpenseRecord", null)
        val type = object : TypeToken<MutableList<ExpenseRecord>>() {}.type
        val savedList: MutableList<ExpenseRecord> = gson.fromJson(json, type) ?: mutableListOf()

        ExpenseRecordData.userExpenseRecord.clear()
        ExpenseRecordData.userExpenseRecord.addAll(savedList)
    }

    private fun saveThemeState(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("themeState", isDarkMode)
        editor.apply()
    }

    private fun loadThemeState(): Boolean {
        return sharedPreferences.getBoolean("themeState", false) // Default to light mode (false)
    }
}


