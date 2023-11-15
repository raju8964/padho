package com.padho.data

sealed class MyResults<out T>{
    data class success<out T>(val data: T):MyResults<T>()
    data class error(val error: String):MyResults<Nothing>()
    object isLoading:MyResults<Nothing>()
}
