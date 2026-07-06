package com.rrain.md5

import com.rrain.util.base.number.ifZero
import java.io.FileWriter




object Results {
  val pathToFileInfo: MutableMap<PathString, FileInfo> = mutableMapOf()
  
  fun printFileInfos(
    console: Boolean = false,
    fileName: String? = null,
  ) {
    val text = StringBuilder()
    
    pathToFileInfo.forEach { p, info ->
      text.appendLine("path: ${info.path}")
      text.appendLine("md5: ${if (info.isError) "ERROR" else info.md5}")
      text.appendLine()
    }
    
    if (console) print(text.toString())
    fileName?.let { name -> FileWriter(name).use { it.write(text.toString()) } }
  }
  
  fun printHashToFileInfosByCntDesc(
    console: Boolean = false,
    fileName: String? = null,
  ) {
    val text = StringBuilder()
    
    pathToFileInfo
      .values
      .groupBy { info -> if (info.isError) "ERROR" else info.md5 }
      .entries
      .sortedWith { (aMd5, aInfos), (bMd5, bInfos) -> bInfos.size.compareTo(aInfos.size)  }
      .forEach { (md5, infos) ->
        text.appendLine("cnt: ${infos.size}")
        text.appendLine("md5: $md5, cnt: ${infos.size}")
        infos.forEach { text.appendLine("path: ${it.path}") }
        text.appendLine()
      }
    
    if (console) print(text.toString())
    fileName?.let { name -> FileWriter(name).use { it.write(text.toString()) } }
  }
  
  fun printRelPathToHashesByCntDescToFileInfosByCntAsc(
    console: Boolean = false,
    fileName: String? = null,
  ) {
    data class PathGroup<T>(val relPath: String, var cnt: Int, val items: T)
    data class Md5Group<T>(val md5: String, var cnt: Int, val items: T)
    
    val text = StringBuilder()
    
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
          .ifZero { a.items.size.compareTo(b.items.size) }
      }
      .forEach {
        text.appendLine("relPath: ${it.relPath}")
        text.appendLine("cnt: ${it.cnt}")
        it.items.forEach {
          text.appendLine("-- md5: ${it.md5}")
          text.appendLine("-- cnt: ${it.cnt}")
          it.items.forEach {
            text.appendLine("-- -- path: ${it.path}")
          }
        }
        text.appendLine()
      }
    
    if (console) print(text.toString())
    fileName?.let { name -> FileWriter(name).use { it.write(text.toString()) } }
  }
}