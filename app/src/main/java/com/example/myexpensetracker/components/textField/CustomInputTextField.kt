package com.example.myexpensetracker.components.textField


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myexpensetracker.R


@Composable
fun CustomTextField(
    textValue: String,
    onTextChanged: (String) -> Unit,
    label: String,
    placeHolder: String,
    readOnly: Boolean = false,
    trailingIcon: Int? = null,
    onClick: (() -> Unit) = {}
) {
    TextField(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable( onClick = onClick) // TODO: need to verify not working onclick
            .fillMaxWidth(),
        value = textValue,
        onValueChange = { newValue ->
            onTextChanged(newValue)
        },
        label = {
            Text(
                text = "$label*",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp
            )
        },
        placeholder = {
            Text(text = placeHolder)
        },
        readOnly = readOnly,
        trailingIcon = {
            if (trailingIcon != null )
            {
                IconButton(
                    onClick = onClick ,
                )
                {
                    Icon(
                        painter = painterResource(
                            trailingIcon
                        ),
                        contentDescription = "Expand Icon",
                    )
                }
            }
        },

        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
    )
}

