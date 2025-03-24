package org.onedroid.txteditor.app.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Editor : Route

    @Serializable
    data object TextFiles : Route

    @Serializable
    data object TextPins : Route

    @Serializable
    data object Settings : Route
}