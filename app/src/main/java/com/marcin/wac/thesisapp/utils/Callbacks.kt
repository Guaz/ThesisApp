package com.marcin.wac.thesisapp.utils

interface BaseCallback {
    fun success()
    fun error()
}

interface BooleanCallback {
    fun success(boolean: Boolean)
    fun error()
}

interface ParamCallback<T> {
    fun success(response: T)
    fun successEmpty(){}
    fun error(){}
    fun tokenExpired(){}
}