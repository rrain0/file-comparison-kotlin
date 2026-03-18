package com.rrain

import com.rrain.md5.ResultType
import com.rrain.md5.Sources
import com.rrain.md5.processFiles



fun main() {
  val sources = Sources(listOf(
    """D:\Path1""",
    """D:\Path2""",
  ))
  processFiles(
    sources,
    ResultType.RelPathToHashesByCntDescToFileInfosByCntAsc,
    console = true,
    file = "files-info.txt",
  )
}
