package com.example.consumeapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kontak(

    val id: Int,
    val nama: String,
    @SerialName("nohp")
    val nohp:  String,
    @SerialName("email")
    val email: String,



)
