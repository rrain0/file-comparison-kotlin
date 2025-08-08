package com.rrain

import com.rrain.md5.ResultType
import com.rrain.md5.Sources
import com.rrain.md5.processFiles



fun main() {
  // val sources = Sources(listOf(
  //   """E:\from SP PHD U2\test1""",
  //   """E:\from SP PHD U2\test2""",
  // ))
  val sources = Sources(listOf(
    """M:\[удалить]\[test]\Fantastic.Beasts.and.Where.to.Find.Them.2016.BDRip.1080p.mkv""",
    """F:\[удалить]\Fantastic.Beasts.and.Where.to.Find.Them.2016.BDRip.1080p.mkv""",
  ))
  processFiles(sources, ResultType.RelPathToHashToFileInfo)
}