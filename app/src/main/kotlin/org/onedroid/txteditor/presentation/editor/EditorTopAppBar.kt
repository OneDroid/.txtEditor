package org.onedroid.txteditor.presentation.editor

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorTopAppBar(
    navController: NavController,
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Info, contentDescription = "Info")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Create, contentDescription = "Edit")
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    )
}