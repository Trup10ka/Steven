package me.trup10ka.steven.server.util

import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.nio.file.Files

fun copyFileIfNotExists(source: InputStream, destination: File)
{
    if (destination.exists())
        return

    Files.copy(source, destination.toPath())
}

fun throwIfFileDoesNotExist(file: File, typeOfFile: String = "File", fileName: String = "")
{
    if (!file.exists())
        throw FileNotFoundException(assembleErrorString(typeOfFile, fileName, file.absolutePath))
}

private fun assembleErrorString(typeOfFile: String, fileName: String, filePath: String)
    = "$typeOfFile ${if (typeOfFile != "File") "file" else ""} ${if (fileName == "") "" else "'$fileName' "}does not exist on path: $filePath"
