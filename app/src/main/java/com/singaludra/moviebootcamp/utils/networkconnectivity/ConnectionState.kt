package com.singaludra.moviebootcamp.utils.networkconnectivity

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}