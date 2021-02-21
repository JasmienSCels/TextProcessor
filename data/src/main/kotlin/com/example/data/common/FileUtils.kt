package com.example.data.common

import android.content.Context
import java.io.File

object FileUtils {

    fun createFileInStorage(context: Context, fileName: String): File? {
        val name = if (!fileName.isBlank()) fileName else "UNKNOWN"
        return File(getAppFilesDir(context), name)
    }

    private fun getAppFilesDir(context: Context): File? {
        val file = context.filesDir
        if (file != null && !file.exists()) file.mkdirs()

        return file
    }

}