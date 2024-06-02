package me.trup10ka.steven.server.util


import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.nio.file.Files

/**
 * Returns a [File] object representing a file in the resources directory.
 *
 * @param path The path to the file in the resources directory.
 * @return A [File] object representing the file in the resources directory.
 */
fun fromResources(path: String) = File("resources/$path")

/**
 * Copies a file from an [InputStream] to a [File] if the file does not already exist.
 *
 * @param source The [InputStream] to copy from.
 * @param destination The [File] to copy to.
 */
fun copyFileIfNotExists(source: InputStream, destination: File)
{
    if (destination.exists())
        return

    Files.copy(source, destination.toPath())
}

/**
 * Throws a [FileNotFoundException] if the file does not exist.
 *
 * @param file The [File] to check for existence.
 * @param typeOfFile The type of file being checked.
 * @param fileName The name of the file being checked.
 */
fun throwIfFileDoesNotExist(file: File, typeOfFile: String = "File", fileName: String = "")
{
    if (!file.exists())
        throw FileNotFoundException(assembleErrorString(typeOfFile, fileName, file.absolutePath))
}

/**
 * Assembles an error string for a specific file which was not found.
 */
private fun assembleErrorString(typeOfFile: String, fileName: String, filePath: String)
    = "$typeOfFile ${if (typeOfFile != "File") "file" else ""} ${if (fileName == "") "" else "'$fileName' "}does not exist on path: $filePath"
