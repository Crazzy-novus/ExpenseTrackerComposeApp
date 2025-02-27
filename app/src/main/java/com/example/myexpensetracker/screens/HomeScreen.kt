package com.example.myexpensetracker.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.R
import com.example.myexpensetracker.activity.CreateExpenseActivity
import com.example.myexpensetracker.activity.MainActivity
import com.example.myexpensetracker.components.AppTopToolBar
import com.example.myexpensetracker.components.CustomCard
import com.example.myexpensetracker.components.buttons.CustomFloatingActionButton
import com.example.myexpensetracker.components.textField.CustomSearchBar
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(
    context: Context,
    themeIcon : Int,
    onThemeIconClicked : () -> Unit
)
{
    val expenseListState = MainActivity.ExpenseRecordData.userExpenseRecord
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        topBar = {
            AppTopToolBar(
                title = stringResource(R.string.Expense_Tracker),
                rightIcon = themeIcon,
                navigationIconId = R.drawable.menu_icon,
                navigationIconAction = {},
                onClickAction = onThemeIconClicked,
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                onClick = {
                    navigateToExpenseRecordScreen(context)
                }
            )
        }
    )
    {innerPaddingValues ->
        Column(
            modifier = Modifier
                .padding(top = innerPaddingValues.calculateTopPadding())
                .padding(horizontal = 20.dp),
        )
        {
            var searchText by remember { mutableStateOf("") }
            CustomSearchBar(
                value = searchText,
                onValueChanged = {
                    searchText = it
                }
            )
            LazyColumn{

                itemsIndexed(expenseListState) {
                        index, item ->  CustomCard(
                    date = item.date,
                    amount = item.amount,
                    category = item.expenseCategory,
                    description = item.description,
                    onClick = {
                        navigateToExpenseRecordScreen(context = context, index)
                    }
                )
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyExpenseTrackerTheme {
//        HomeScreen(innerPaddingValues = PaddingValues(0.dp), 1)
    }
}

fun navigateToExpenseRecordScreen(context: Context, recordId : Int? = null)
{
    val intent = Intent(context, CreateExpenseActivity::class.java)
    if (recordId != null) {
        intent.putExtra("recordId", recordId.toString())
    }
    context.startActivity(intent)


}