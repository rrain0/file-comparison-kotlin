package com.rrain.md5

import java.io.File
import java.io.IOException
import kotlin.io.path.Path
import kotlin.io.path.relativeTo



fun collectFilesInfo(path: PathString) {
  collectFilesInfoRecursive(File(path), CurrInfo(path))
}

private fun collectFilesInfoRecursive(file: File, currInfo: CurrInfo) {
  when {
    file.isDirectory -> file.listFiles().forEach {
      collectFilesInfoRecursive(it, currInfo)
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
      Results.pathToFileInfo[fileInfo.path] = fileInfo
    }
  }
}


data class CurrInfo(
  val sourcePath: PathString,
)
