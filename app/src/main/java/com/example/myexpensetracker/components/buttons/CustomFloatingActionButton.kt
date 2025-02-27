package com.example.myexpensetracker.components.buttons


import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.myexpensetracker.R

@Composable
fun CustomFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
    ) {
        Icon(painter = painterResource(
            R.drawable.floating_action_btn),
            "Floating action button."
        )
    }
}

