package com.example.myexpensetracker.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.components.textView.HeadingText
import com.example.myexpensetracker.components.textView.NormalText
import com.example.myexpensetracker.models.ExpenseRecord
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme
@Composable
fun CustomCard(
    item : ExpenseRecord,
    onClick: () -> Unit
)
{
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable(onClick = onClick),
        shape = RectangleShape
    )
    {
        val headingTextModifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onSurface)
            .padding(vertical = 5.dp, horizontal = 10.dp)
        val normalTextModifier = Modifier
            .padding(vertical = 5.dp, horizontal = 10.dp)

//        HeadingText(
//            text = item.date,
//            modifier = headingTextModifier,
//            textAlignment = TextAlign.Start
//        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            NormalText(
                text = item.expenseCategory,
                modifier = normalTextModifier,
                textAlignment = TextAlign.Start
            )

            NormalText(
                text = "Rs.${item.amount}",
                modifier = normalTextModifier,
                textAlignment = TextAlign.Start
            )
        }
        NormalText(
            text = item.description,
            modifier = normalTextModifier,
            textAlignment = TextAlign.End
        )
        HorizontalDivider(thickness = 2.dp)

    }
}

@Preview
@Composable
fun Hello()
{
    MyExpenseTrackerTheme()
    {
//        RowDemo()
        CustomCard(ExpenseRecord(), onClick = {})
    }
}