package com.example.marketpulse.core

sealed class Resource<out T> {
    object Idle : Resource<Nothing>()
    object Loading: Resource<Nothing>()
    data class Success<out Q>(val data: Q) : Resource<Q>()
    data class Error(val message: String) : Resource<Nothing>()
}
