package com.rrain.md5



fun processFiles(sources: Sources, resultType: ResultType) {
  sources.list.forEach { collectFilesInfo(it) }
  
  when (resultType) {
    ResultType.FileInfo -> Results.printFileInfos()
    ResultType.HashToFileInfo -> Results.printHashToFileInfos()
    ResultType.RelPathToHashToFileInfo -> Results.printRelPathToHashToFileInfo()
  }
}


enum class ResultType {
  FileInfo,
  HashToFileInfo,
  RelPathToHashToFileInfo,
}