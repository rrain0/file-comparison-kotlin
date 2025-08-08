package com.rrain.md5

import java.io.File



fun processFiles(sources: Sources) {
  sources.list.forEach { collectFilesInfo(it) }
  Results.printHashInfos()
}