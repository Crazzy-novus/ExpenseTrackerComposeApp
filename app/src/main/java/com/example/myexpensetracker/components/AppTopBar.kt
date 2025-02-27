package com.example.myexpensetracker.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexpensetracker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopToolBar(
    title : String,
    rightIcon : Int? = null,
    onClickAction: (() -> Unit)? = null,
    navigationIconId: Int,
    navigationIconAction: () -> Unit,

)
{
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigationIconAction
            )
            {
                Icon(painter = painterResource(navigationIconId),
                    contentDescription = "backIcon",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(7.dp)
                )
            }
        },
        actions = {

            if (rightIcon != null && onClickAction != null ) {
                IconButton(
                    onClick = onClickAction
                )
                {
                    Icon(painter = painterResource(
                        rightIcon
                    ),
                        "backIcon",
                        modifier = Modifier
                            .padding(7.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview()
{
//   AppTopToolBar(stringResource(R.string.Expense_Tracker) ,R.drawable.light_theme_icon_50,{}, {})
}