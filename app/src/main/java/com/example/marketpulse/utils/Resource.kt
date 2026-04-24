package com.example.marketpulse.utils

sealed class Resource<out T> {
    object Idle : Resource<Nothing>() //Representa "esperando interacción"
    object Loading: Resource<Nothing>()
    data class Success<out Q>(val data: Q) : Resource<Q>()
    //la Q de la data class esta conectada a la T de Resource debido a que hereda de la misma
    //por eso si defino a la T de Resource como Unit la Q de Success tambien debe ser Unit
    //Nothing es el subtipo de todas las clases en Kotlin
    data class Error(val message: String) : Resource<Nothing>()
}


