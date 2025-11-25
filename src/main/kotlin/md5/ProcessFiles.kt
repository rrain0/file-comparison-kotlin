package com.rrain.md5



fun processFiles(
  sources: Sources,
  resultType: ResultType,
  console: Boolean = false,
  file: String? = null,
) {
  // Прочитать файлы и собрать инфу о них
  sources.list.forEach { collectFilesInfo(it) }
  
  // Обработать и вывести инфу о файлах
  when (resultType) {
    ResultType.FileInfo -> Results.printFileInfos(console, file)
    ResultType.HashToFileInfo -> Results.printHashToFileInfos(console, file)
    ResultType.RelPathToHashToFileInfo -> Results.printRelPathToHashToFileInfo(console, file)
  }
}


enum class ResultType {
  FileInfo,
  HashToFileInfo,
  RelPathToHashToFileInfo,
}