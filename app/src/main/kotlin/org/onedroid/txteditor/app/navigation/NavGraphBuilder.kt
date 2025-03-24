package org.onedroid.txteditor.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.onedroid.txteditor.presentation.editor.TextEditorScreen

fun NavGraphBuilder.navGraphBuilder(
    rootNavController: NavController,
    innerPadding: PaddingValues
) {
    composable<Route.Editor> {
        TextEditorScreen(
            contentPadding = innerPadding,
            navController = rootNavController
        )
    }
    composable<Route.TextFiles> {
        Text(text = "Text Files")
    }
    composable<Route.TextPins> {
        Text(text = "Text Pins")
    }
    composable<Route.Settings> {
        Text(text = "Settings")
    }
}