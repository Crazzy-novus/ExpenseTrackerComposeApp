package com.example.myexpensetracker.components.textView

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun <T> RupeeText(
    text : T,
    modifier: Modifier,
    textAlignment : TextAlign
)
{
    Text(
        text = "Rs.$text",
        modifier = modifier,
        textAlign = textAlignment,
    )

}
