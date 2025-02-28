package com.example.myexpensetracker.screens


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.R
import com.example.myexpensetracker.activity.CreateExpenseActivity
import com.example.myexpensetracker.components.AppTopToolBar
import com.example.myexpensetracker.components.CustomCard
import com.example.myexpensetracker.components.buttons.CustomFloatingActionButton
import com.example.myexpensetracker.components.textField.CustomSearchBar
import com.example.myexpensetracker.components.textView.HeadingText
import com.example.myexpensetracker.models.ExpenseRecord
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    context: Context,
    userExpenseRecordList : MutableList<ExpenseRecord>,
    categoryList : ArrayList<String>,
    themeIcon : Int,
    onThemeIconClicked : () -> Unit
)
{
    var temUserExpenseRecordList = userExpenseRecordList
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult())
    { result ->
        // TODO need to find Alternate
        if (result.resultCode == Activity.RESULT_OK)
        {
            val record = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            {
                result.data?.getParcelableExtra("recordObject", ExpenseRecord::class.java)
            }
            else
            {
                @Suppress("DEPRECATION")
                result.data?.getParcelableExtra("recordObject")
            }

            record?.let { newRecord ->
                val recordIndex =
                    userExpenseRecordList.indexOfFirst { it.recordId == newRecord.recordId }
                if (recordIndex != -1) {
                    userExpenseRecordList[recordIndex] = newRecord // Update existing record
                }
                else
                {
                    userExpenseRecordList.add(newRecord) // Add new record
                }
            }
        }
    }

        Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {

            AppTopToolBar(
                title = stringResource(R.string.Expense_Tracker),
                rightIcon = themeIcon,
                onClickAction = onThemeIconClicked,
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                onClick = {
                    navigateToExpenseRecordScreen(
                        context = context,
                        launcher = launcher,
                        categoryList = categoryList
                    )
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
            // Local Variable to store Search text
            var searchText by remember { mutableStateOf("") }

            CustomSearchBar(
                value = searchText,
                onValueChanged = {
                    searchText = it
                    temUserExpenseRecordList = filterListBySearchQuery(it, userExpenseRecordList)
                }
            )
            val categorizedList = temUserExpenseRecordList.groupBy { it.date }
            LazyColumn()
            {
                categorizedList.forEach { (header, list) ->
                    stickyHeader()
                    {
                        HeadingText(
                            text = header,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.surfaceDim)
                                .padding(vertical = 5.dp, horizontal = 10.dp),
                            textAlignment = TextAlign.Start
                        )
                    }
                    items(list)
                    {
                        CustomCard(
                            item = it,
                            onClick = {
                                navigateToExpenseRecordScreen(context = context, launcher, it, categoryList)
                            }
                        )
                    }
                }
            }
        }
    }
}

fun filterListBySearchQuery(
    searchQuery : String,
    userExpenseList : MutableList<ExpenseRecord>
) : MutableList<ExpenseRecord>
{
    return userExpenseList.filter { it.expenseCategory.startsWith(searchQuery, ignoreCase = true) || it.date.startsWith(searchQuery) }.toMutableList()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview()
{
    MyExpenseTrackerTheme ()
    {
//        HomeScreen(this,)
    }
}
// Function takes expenseRecord If supplied or it create default as empty object
// TODO Need to verify which is optimized passing empty object or check if it available in second Activity
fun navigateToExpenseRecordScreen(
    context: Context,
    launcher: ActivityResultLauncher<Intent>,
    expenseRecord : ExpenseRecord = ExpenseRecord(),
    categoryList : ArrayList<String>
)
{
    val intent = Intent(context, CreateExpenseActivity::class.java).apply {
        putExtra(R.string.expense_record_key.toString(), expenseRecord)
        putStringArrayListExtra(R.string.category_list_key.toString(), categoryList)
    }
    launcher.launch(intent)
}