package com.example.myexpensetracker.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.components.textView.NormalText

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetList(
    expenseCategoryList : MutableList<String>,
    onDismissRequest: () -> Unit,
    onItemSelected: (String) -> Unit
)
{
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {

        horizontalGridList(
            list = expenseCategoryList,
            onItemClick = { selectedCategory ->
                onItemSelected(selectedCategory)
            }
        )
    }

}
