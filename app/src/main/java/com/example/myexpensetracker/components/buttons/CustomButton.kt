package com.example.myexpensetracker.components.buttons


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myexpensetracker.ui.theme.MyExpenseTrackerTheme

@Composable
fun CustomButton(
    text : String,
    onClickAction: () -> Unit
)
{
    Button(onClick = onClickAction,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
    )
    {
        Text(
            text = text,
            fontSize = 22.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DisplayMode2()
{
//    TextView("Amount")
    MyExpenseTrackerTheme {
        CustomButton("Hello", {})
    }

}