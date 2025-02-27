package com.example.myexpensetracker.components.textView

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun <T> HeadingText(
    text : T,
    modifier: Modifier,
    textAlignment : TextAlign
)
{
    Text(
        text = "$text",
        modifier = modifier,
        textAlign = textAlignment,
        fontWeight = FontWeight.Bold
    )

}