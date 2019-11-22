package com.mimecast.tronalddump.domain.entities

data class Tags (
    val count: Int,
    val total: Int,
    val _embedded: List<String>
)