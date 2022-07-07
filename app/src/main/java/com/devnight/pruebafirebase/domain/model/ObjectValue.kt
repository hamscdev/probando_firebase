package com.devnight.pruebafirebase.domain.model

data class ObjectValue(
    val direct: List<Directions>?
    )

data class Directions(val direccion: String, val latitud: String, val longitud: String)