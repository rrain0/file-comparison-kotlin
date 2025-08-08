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
  
  fun printRelPathToHashToFileInfo0() {
    pathToFileInfo
      .values
      .groupBy { info -> info.relPath }
      .entries
      .sortedWith { (relPathA, a), (relPathB, b) ->
        relPathA.compareTo(relPathB)
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
  
  fun printRelPathToHashToFileInfo() {
    data class PathGroup<T>(val relPath: String, var cnt: Int, val items: T)
    data class Md5Group<T>(val md5: String, var cnt: Int, val items: T)
    pathToFileInfo
      .values
      .groupBy { group -> group.relPath }
      .map { (relPath, infos) -> PathGroup(
        relPath,
        infos.size,
        infos
          .groupBy { it.md5 ?: "ERROR" }
          .map { (md5, infos) -> Md5Group(md5, infos.size, infos) }
      ) }
      .sortedWith { a, b ->
        b.cnt.compareTo(a.cnt)
          .mapZero { a.items.size.compareTo(b.items.size) }
      }
      .forEach {
        println("relPath: ${it.relPath}")
        println("cnt: ${it.cnt}")
        it.items.forEach {
          println("-- md5: ${it.md5}")
          println("-- cnt: ${it.cnt}")
          it.items.forEach {
            println("-- -- path: ${it.path}")
          }
        }
        println()
      }
  }
}