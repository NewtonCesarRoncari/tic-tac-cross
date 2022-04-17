package com.nroncari.tictaccross.utils

interface Mapper<S, T> {
    fun map(source: S): T
}
