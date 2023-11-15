package com.padho.data

data class AuthState(
    var isloading: Boolean = false,
    var isError: String="",
    val data: Any?=null
)