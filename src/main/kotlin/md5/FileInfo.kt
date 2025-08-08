package com.rrain.md5




data class FileInfo (
  val path: PathString,
  val sourcePath: PathString,
  val sourceRelativePath: PathString,
  val isError: Boolean = false,
  val md5: String? = null,
)
