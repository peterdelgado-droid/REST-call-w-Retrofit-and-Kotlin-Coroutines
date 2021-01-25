package com.e.herolist.data.listener

interface APIListener<T> {
    fun onSuccess(model: T)
    fun onFailure(str: String)
}