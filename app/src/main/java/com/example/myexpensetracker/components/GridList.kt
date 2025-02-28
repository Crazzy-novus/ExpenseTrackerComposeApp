package com.example.myexpensetracker.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme

@Composable
fun horizontalGridList(
    list : MutableList<String>,
    onItemClick: (String) -> Unit
)
{
    LazyVerticalGrid (
        modifier = Modifier.padding(10.dp),
        columns = GridCells.Adaptive(100.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    )
    {
        items(list)
        {
            Text(
                text = it,
                modifier = Modifier
                    .border(1.dp, Color.Blue, shape = RoundedCornerShape(12.dp))
                    .padding(5.dp)
                    .wrapContentSize(align = Alignment.Center)
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .clickable {onItemClick(it)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalGridListPreview()
{
    MyExpenseTrackerTheme()
    {
//        HorizontalGridList(mutableListOf("Fooddddddd","Petrol","Trip","Food","Petrol","Trip"),{},{})

    }
}
