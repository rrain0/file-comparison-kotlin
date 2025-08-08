package com.rrain.md5

import java.io.File
import java.io.FileInputStream



fun fileToMd5(file: File): String {
  val maxArrLen = (Int.MAX_VALUE / 2).toLong()
  var len = file.length()
  FileInputStream(file).use {
    Md5.reset()
    while (len > 0) {
      val partLen = minOf(len, maxArrLen).toInt()
      len -= partLen
      val part = it.readNBytes(partLen)
      Md5.addPart(part)
    }
  }
  return Md5.md5()
}