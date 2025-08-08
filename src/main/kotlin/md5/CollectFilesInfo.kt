package com.rrain.md5

import java.io.File
import java.io.IOException
import kotlin.io.path.Path
import kotlin.io.path.relativeTo



fun collectFilesInfo(path: PathString) {
  val result: MutableMap<PathString, FileInfo> = mutableMapOf()
  collectFilesInfoRecursive(File(path), CurrInfo(path), result)
  Results.pathToFileInfo += result
}

private fun collectFilesInfoRecursive(
  file: File,
  currInfo: CurrInfo,
  result: MutableMap<PathString, FileInfo>
) {
  when {
    file.isDirectory -> file.listFiles().forEach {
      collectFilesInfoRecursive(it, currInfo, result)
    }
    file.isFile -> {
      val fileInfo =
        try {
          val md5 = fileToMd5(file)
          FileInfo(
            file.absolutePath,
            currInfo.sourcePath,
            Path(file.absolutePath).relativeTo(Path(currInfo.sourcePath)).toString(),
            md5 = md5
          )
        }
        catch (ex: IOException) {
          FileInfo(
            file.absolutePath,
            currInfo.sourcePath,
            Path(file.absolutePath).relativeTo(Path(currInfo.sourcePath)).toString(),
            isError = true
          )
        }
      result[fileInfo.path] = fileInfo
    }
  }
}



data class FileInfo (
  val path: PathString,
  val sourcePath: PathString,
  val relPath: PathString,
  val isError: Boolean = false,
  val md5: String? = null,
)



data class CurrInfo(
  val sourcePath: PathString,
)
