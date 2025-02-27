package com.example.myexpensetracker.components

import androidx.annotation.Dimension
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myexpensetracker.R
import com.example.myexpensetracker.components.textField.CustomSearchBar
import com.example.myexpensetracker.components.textView.NormalText

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListBottomList(
    onDismissRequest: () -> Unit
)
{
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight()
            .padding(horizontal = 10.dp),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        // TODO: Need to Implement Category List
        NormalText(
            text = "1.Food",
            modifier = Modifier.padding(20.dp),
            textAlignment = TextAlign.Start
        )
    }

}
