package com.mazharulsabbir.model

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class User(
    var id: String = UUID.randomUUID().toString(),
    val firstName: String?,
    val lastName: String?,
    val email: String?
)

val userStorage = mutableListOf<User>()
