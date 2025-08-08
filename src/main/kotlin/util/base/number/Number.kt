package com.rrain.util.base.number



fun Int.mapZero(block: () -> Int): Int = if (this == 0) block() else this