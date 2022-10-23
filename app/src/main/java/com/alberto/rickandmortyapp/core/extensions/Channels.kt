package com.alberto.rickandmortyapp.core.extensions

import kotlinx.coroutines.channels.SendChannel

suspend fun <E> SendChannel<E>.tryOffer(element: E): Boolean = try {
    send(element)
    true
} catch (t: Throwable) {
    false
}