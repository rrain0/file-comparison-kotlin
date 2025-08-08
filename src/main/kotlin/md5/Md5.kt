package com.rrain.md5

import java.math.BigInteger
import java.security.MessageDigest



object Md5 {
  private val md = MessageDigest.getInstance("MD5")
  
  fun addPart(byteArray: ByteArray) = md.update(byteArray)
  
  // get md5 and reset
  fun md5(): String {
    val md5Bytes = md.digest()
    val md5 = BigInteger(1, md5Bytes).toString(16)
    val md5Formatted = md5.padStart(32, '0').lowercase().chunked(8).joinToString("-")
    return md5Formatted
  }
  
  fun reset() = md.reset()
}