package com.rrain.md5



fun processFiles(
  sources: Sources,
  resultType: ResultType,
  console: Boolean = false,
  fileName: String? = null,
) {
  // Прочитать файлы и собрать инфу о них
  sources.list.forEach { collectFilesInfo(it) }
  
  // Обработать и вывести инфу о файлах
  when (resultType) {
    ResultType.FileInfo -> Results.printFileInfos(console, fileName)
    ResultType.HashToFileInfosByCntDesc -> Results.printHashToFileInfosByCntDesc(console, fileName)
    ResultType.RelPathToHashesByCntDescToFileInfosByCntAsc -> Results.printRelPathToHashesByCntDescToFileInfosByCntAsc(console, fileName)
  }
}


enum class ResultType {
  FileInfo,
  HashToFileInfosByCntDesc,
  RelPathToHashesByCntDescToFileInfosByCntAsc,
}