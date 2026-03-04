package com.rrain.util.base.number



inline infix fun Int.ifZero(block: () -> Int): Int {
  return if (this == 0) block() else this
}