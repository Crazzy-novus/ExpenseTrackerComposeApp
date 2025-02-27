package com.example.myexpensetracker.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.components.textView.HeadingText
import com.example.myexpensetracker.components.textView.NormalText
import com.example.myexpensetracker.components.textView.RupeeText
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme
@Composable
fun<T> CustomCard(
    date : T,
    amount : T,
    category : T,
    description : T,
    onClick: () -> Unit
)
{
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(3.dp)
            .clickable(onClick = onClick) //TODO("Need to navigate to edit Screen")
    )
    {
        val headingTextModifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceDim)
            .padding(vertical = 5.dp, horizontal = 10.dp)
        val normalTextModifier = Modifier
            .padding(vertical = 5.dp, horizontal = 10.dp)

        HeadingText(
            text = date,
            modifier = headingTextModifier,
            textAlignment = TextAlign.Start
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            NormalText(
                text = category,
                modifier = normalTextModifier,
                textAlignment = TextAlign.Start
            )
            RupeeText(
                text = amount,
                modifier = normalTextModifier,
                textAlignment = TextAlign.End
            )
        }
        NormalText(
            text = description,
            modifier = normalTextModifier,
            textAlignment = TextAlign.End
        )

    }
}

@Preview
@Composable
fun Hello()
{
    MyExpenseTrackerTheme {
//        RowDemo()
        CustomCard("Date","amount","category","description", onClick = {})
    }
}