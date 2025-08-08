package com.rrain.md5




object Results {
  val pathToFileInfo: MutableMap<PathString, FileInfo> = mutableMapOf()
  
  fun printFileInfos() {
    pathToFileInfo.forEach { p, info ->
      println("path: ${info.path}")
      println("md5: ${if (info.isError) "ERROR" else info.md5}")
      println()
    }
  }
  
  fun printHashInfos() {
    pathToFileInfo
      .values
      .groupBy { info -> if (info.isError) "ERROR" else info.md5 }
      .entries
      .sortedWith { (aMd5, aInfos), (bMd5, bInfos) -> bInfos.size - aInfos.size  }
      .forEach { (md5, infos) ->
        println("cnt: ${infos.size}")
        println("md5: $md5, cnt: ${infos.size}")
        infos.forEach { println("path: ${it.path}") }
        println()
      }
  }
  
  fun printSubPathToHashToFileInfo() {
  
  }
}