package org.onedroid.txteditor.presentation.editor

import android.view.ViewGroup
import android.widget.ScrollView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import io.github.rosemoe.sora.widget.CodeEditor
import org.onedroid.txteditor.app.navigation.Route
import org.onedroid.txteditor.app.navigation.components.BottomNavigationBar
import org.onedroid.txteditor.app.navigation.components.getBottomNavigationItems
import org.onedroid.txteditor.app.navigation.navGraphBuilder

@Composable
fun TextEditorScreen(
    contentPadding: PaddingValues,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { EditorTopAppBar(navController = navController, title = "Text Editor") },
    ) { innerPadding ->
        val imeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (imeVisible) 0.dp else contentPadding.calculateBottomPadding())
                .padding(innerPadding)
        ) {
            TextEditorView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Makes it expandable
                    .imePadding() // Prevents hiding behind keyboard
            )
        }
    }
}


@Composable
private fun TextEditorView(
    modifier: Modifier = Modifier,
    initialText: String = "Hello",
    language: String? = null,
    editable: Boolean = true,
    onTextChanged: (String) -> Unit = {},
) {
    var editor by remember { mutableStateOf<CodeEditor?>(null) }
    val focusRequester = remember { FocusRequester() }

    // Get the current focus manager
    val focusManager = LocalFocusManager.current

    // Get the keyboard controller to detect when keyboard is shown
    val keyboardController = LocalSoftwareKeyboardController.current

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            ScrollView(ctx).apply {
                isFillViewport = true // Allows resizing to fit keyboard
                addView(CodeEditor(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setText(initialText)
                    isEditable = editable
                    setTextSize(14f)
                    isWordwrap = true
                    setPinLineNumber(true)
                    editor = this
                })
            }
        }
    )
}