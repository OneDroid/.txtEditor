package org.onedroid.txteditor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.onedroid.txteditor.app.navigation.Route
import org.onedroid.txteditor.app.theme.TxtEditorTheme
import org.onedroid.txteditor.app.navigation.components.BottomNavigationBar
import org.onedroid.txteditor.app.navigation.components.getBottomNavigationItems
import org.onedroid.txteditor.app.navigation.navGraphBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TxtEditorTheme {
                val rootNavController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            items = getBottomNavigationItems(),
                            navController = rootNavController,
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = rootNavController,
                        startDestination = Route.Editor,
                    ) {
                        navGraphBuilder(
                            rootNavController = rootNavController,
                            innerPadding = innerPadding
                        )
                    }
                }
            }
        }
    }
}