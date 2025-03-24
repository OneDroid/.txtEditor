package org.onedroid.txteditor.app.navigation.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import org.onedroid.txteditor.R
import org.onedroid.txteditor.app.navigation.Route

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: Route
)

@Composable
private fun getDrawableVector(resourceId: Int): ImageVector {
    return ImageVector.vectorResource(id = resourceId)
}

@Composable
fun getBottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = ".txtEditor",
            selectedIcon = getDrawableVector(R.drawable.ic_editor),
            unselectedIcon = getDrawableVector(R.drawable.ic_editor),
            route = Route.Editor
        ),
        BottomNavigationItem(
            title = ".txtFiles",
            selectedIcon = getDrawableVector(R.drawable.ic_baseline_text_snippet),
            unselectedIcon = getDrawableVector(R.drawable.ic_outline_text_snippet),
            badgeCount = 80,
            route = Route.TextFiles
        ),
        BottomNavigationItem(
            title = ".txtPins",
            selectedIcon = getDrawableVector(R.drawable.ic_baseline_pin),
            unselectedIcon = getDrawableVector(R.drawable.ic_outline_pin),
            badgeCount = 10,
            route = Route.TextPins
        )
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationItem>,
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}