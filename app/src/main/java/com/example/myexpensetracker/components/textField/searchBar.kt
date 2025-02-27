package com.example.myexpensetracker.components.textField

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.R

@Composable
fun CustomSearchBar(
    value : String,
    onValueChanged : (String) -> Unit,
)
{
    TextField(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .border(1.dp, color = MaterialTheme.colorScheme.onPrimary),
        value = value,
        leadingIcon = {

            Icon(
                painter = painterResource(R.drawable.search_iocn),
                contentDescription = "Search Icon",
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {

            Text(
                text = stringResource(R.string.search)
            )
        },
        onValueChange = { newValue -> onValueChanged (newValue) },
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun DisplaySearchBar()
{
    CustomSearchBar("hello"){}
}