package com.rrain.md5

import com.rrain.util.base.number.mapZero




object Results {
  val pathToFileInfo: MutableMap<PathString, FileInfo> = mutableMapOf()
  
  fun printFileInfos() {
    pathToFileInfo.forEach { p, info ->
      println("path: ${info.path}")
      println("md5: ${if (info.isError) "ERROR" else info.md5}")
      println()
    }
  }
  
  fun printHashToFileInfos() {
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
  
  fun printRelPathToHashToFileInfo() {
    pathToFileInfo
      .values
      .groupBy { info -> info.relPath }
      .entries
      .sortedWith { (relPathA, a), (relPathB, b) ->
        a.first().relPath.compareTo(b.first().relPath)
          .mapZero { b.size.compareTo(a.size) }
      }
      .map { (relPath, infos) -> relPath to infos.groupBy { info -> info.md5 } }
      .forEach { (relPath, md5ToInfos) ->
        println("relPath: $relPath")
        md5ToInfos.forEach { (md5, infos) ->
          println("-- md5: $md5")
          println("-- cnt: ${infos.size}")
          infos.forEach {
            println("-- -- path: ${it.path}")
          }
        }
        println()
      }
  }
}