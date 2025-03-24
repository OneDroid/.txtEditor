package org.onedroid.txteditor.domain.model

data class TextFile(
    val id: String,
    val name: String,
    val content: String,
    val lastModified: Long
)