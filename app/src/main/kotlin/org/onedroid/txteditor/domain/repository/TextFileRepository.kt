package org.onedroid.txteditor.domain.repository

import kotlinx.coroutines.flow.Flow
import org.onedroid.txteditor.domain.model.TextFile

interface TextFileRepository {
    fun getAllTextFiles(): Flow<List<TextFile>>
    suspend fun getTextFileById(id: String): TextFile?
    suspend fun saveTextFile(textFile: TextFile)
    suspend fun deleteTextFile(id: String)
    suspend fun createTextFile(name: String): TextFile
}