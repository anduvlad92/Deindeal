package com.deindeal.app.features.base

class Resource<T>(val state: STATE, val resource: T?) {
    enum class STATE {
        SUCCESS, ERROR
    }
}